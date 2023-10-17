package StepDefinitions;

import Pages.*;
import Utils.ConfigurationReader;
import Utils.Driver;
import Utils.MyUtils;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class E2E_Orders_StepDef {

    Random random = new Random();
    private MainPage mp = new MainPage();
    private LoginPage lp = new LoginPage();
    private SQLPage sp = new SQLPage();
    private ProductPage pp = new ProductPage();
    private PaypalPage payp = new PaypalPage();
    private OrdersPage op = new OrdersPage();
    private SoldItemsPage sip = new SoldItemsPage();
    private static String addedProductHub;
    private static int addedProductHubNumber;
    private static String addedProductName;
    private final String addedProductPrice = "13";
    private final String addedProductStock = "41";
    private static String  myBlock;
    private static String orderedProductNo;
    private static String soldProductNo;

    @Given("Navigate to {string}")
    public void navigateTo(String URL) {
        Driver.getDriver().get(URL);
    }

    @When("Login with valid credentials of the {string}")
    public void loginWithValidCredentialsOfThe(String user) {
        String eMail = null, password = null;
        if (user.toLowerCase().equals("seller")) {
            eMail = "seller_urban@mailsac.com";
            password = "VHt*zzt*wQNu6XS";

        } else if (user.toLowerCase().equals("buyer")) {
            eMail = "buyer_urban@mailsac.com";
            password = "VHt*zzt*wQNu6XS";
        }

        MyUtils.clickFunction(mp.getElement("loginButton"));
        MyUtils.sendKeysFunction(lp.getElement("loginEMail"), eMail);
        MyUtils.sendKeysFunction(lp.getElement("loginPassword"), password);
        MyUtils.clickFunction(lp.getElement("loginSubmitButton"));

        try {
            MyUtils.waitUntilClickable(mp.getElement("zipCodeButton"));
        } catch (Exception e) {
            MyUtils.clickFunction(mp.getElement("changeZIPCodeButton"));
        }

        MyUtils.waitUntilClickable(mp.getElement("zipCodeButton"));
        MyUtils.sendKeysFunction(mp.getElement("inputZipCode"),"95170");
        MyUtils.clickFunction(mp.getElement("zipCodeButton"));

//        Robot robot = new Robot();              // Edge alert için eklendi
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Then("Go to Your products or services page")
    public void goToYourProductsOrServicesPage() {
        MyUtils.clickFunction(mp.getElement("sideBarProductButton"));
    }

    @And("Add a new product to sell")
    public void addANewProductToSell() {
        Boolean verifyAdd = false;  // ürün ekleme yapılana kadar devam etsin
        int listSize = mp.getElementList("hubList").size();
        addedProductHubNumber = random.nextInt(listSize);
        addedProductHub = MyUtils.getElementsAttribute(mp.getElementList("hubList"), "hubuniquename")
                .get(addedProductHubNumber);
        MyUtils.clickFunction(mp.getElementList("hubList").get(addedProductHubNumber));

        for (int i = 0; !verifyAdd; i++) {
            int randomNumber;
            if (listSize == 0) {
            }   //  hub'a ürün grubu ekleme metodu için
            else {
                int datalistSize = mp.getElementList("dataList").size();
                randomNumber = random.nextInt(datalistSize);

                MyUtils.scrollToElement(mp.getElement("inputProductName"));
                MyUtils.waitFor(500);
                MyUtils.clickWithActions(mp.getElement("inputProductName"));
                addedProductName = mp.getElementList("dataList").get(randomNumber).getAttribute("value");

                MyUtils.clickWithActions(mp.getElement("inputProductName"));
                MyUtils.sendKeysWithActions(mp.getElement("inputProductName"), addedProductName);
                MyUtils.sendKeysFunction(mp.getElement("inputProductPrice"), addedProductPrice);
                MyUtils.sendKeysFunction(mp.getElement("inputProductStock"), addedProductStock);
                MyUtils.clickWithJS(mp.getElement("submitButton"));

                String alertMessage = MyUtils.getElementText(mp.getElement("productAddedAlert"));

                if (alertMessage.contains("added")) {
                    verifyAdd = true;
                } else {
                    MyUtils.waitFor(3000);
                    mp.getElement("inputProductName").clear();

                    if (i > 1) {
                        MyUtils.scrollToTop();
                        addedProductName = mp.getElementList("currentProductsInHub").get(0).getText();
                        MyUtils.clickFunction(mp.getElementList("currentProductsInHub").get(0));
                        MyUtils.sendKeysFunction(mp.getElement("updateAddingProductPrice"), addedProductPrice);
                        MyUtils.sendKeysFunction(mp.getElement("updateAddingProductStock"), addedProductStock);
                        MyUtils.clickFunction(mp.getElement("updateAddingProductButton"));
                        alertMessage = MyUtils.getElementText(mp.getElement("updateAlert"));
                        verifyAdd = true;
                        Assert.assertTrue(alertMessage.contains("successful"));
                    }
                }
            }
        }
    }

    @Then("Verify that the product added message is available")
    public void verifyThatTheProductAddedMessageIsAvailable() {
        String alertMessage=MyUtils.getElementText(mp.getElement("productAddedAlert"));
        Assert.assertTrue(MyUtils.isAnyStringInText(alertMessage, "successful", "added"));
    }

    @Then("Open a new frame and select to navigate")
    public void openANewFrameAndSelectToNavigate() {
        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    @And("Login SQL with valid credentials")
    public void loginSQLWithValidCredentials() {
        MyUtils.sendKeysFunction(sp.getElement("userName"),"urbanicfarm_mysql_user");
        MyUtils.sendKeysFunction(sp.getElement("userPassword"),"urbanicfarm_mysql_password");
        MyUtils.clickWithActions(sp.getElement("loginButton"));
    }

    @Then("Select database to execute query")
    public void selectDatabaseToExecuteQuery() {
        MyUtils.clickWithJS(sp.getElement("databaseButton"));
        MyUtils.clickWithJS(sp.getElement("urbanicFarmDataButton"));
        MyUtils.clickWithJS(sp.getElement("sqlButton"));

    }

    @And("Execute SQL query to fix as APPROVED")
    public void executeSQLQueryToFixAsAPPROVED() {
        String sqlQuery="UPDATE hub_product SET product_listing_state = 'APPROVED' WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM hub_product WHERE (price = '" + addedProductPrice +"' AND stock = '"+ addedProductStock +"')) AS subquery) AND product_listing_state = 'IN_REVIEW';";
        System.out.println("addedProductName = " + addedProductName);
        MyUtils.sendKeysWithActions(sp.getElement("queryLine"), sqlQuery);
        MyUtils.clickWithActions(sp.getElement("submitQueryButton"));
        String textContent = sp.getElement("queryResultText").getAttribute("textContent");
        Assert.assertTrue(textContent.contains("1 satır etkilendi."));
    }

    @And("Close the browser tab and back to the UrbanicFarm frame")
    public void closeTheBrowserTabAndBackToTheUrbanicFarmFrame() {
        Driver.getDriver().close();
    }

    @And("Verify related item is available with APPROVED description")
    public void verifyRelatedItemIsAvailableWithAPPROVEDDescription() {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[0].toString());
        MyUtils.clickFunction(mp.getElement("sideBarProductButton"));
        WebElement hubElement= Driver.getDriver().findElement(By.cssSelector("svg[hubuniquename='"+addedProductHub+"']"));
        MyUtils.clickFunction(hubElement);

        String myHubData = "APPROVED\n" + addedProductName + "\nPrice: $" + addedProductPrice +
                           "\nStock: " + addedProductStock + " Pound";
        System.out.println("Product Descriptions = \n" + myHubData);

        int myHubDataListLen=mp.getElementList("hubNames").size();

        String verifyMessage = "Product is already IN_REVIEW";
        String myHubName1;
        String siraNo=null;
        siraNo = MyUtils.getElementsAttributeNo(mp.getElementList("hubNames"), "innerText", myHubData);
        int sira=Integer.parseInt(siraNo);

        for (int i=0; i<myHubDataListLen; i++) {
            myHubName1 = mp.getElementList("hubNames").get(i).getAttribute("innerText");

            if (myHubName1.equals(myHubData)) {
                verifyMessage ="Product is available as APPROVED";
            }
        }
        Assert.assertEquals(verifyMessage,"Product is available as APPROVED");

    }

    @And("Logout from website")
    public void logoutFromWebsite() {
        MyUtils.clickFunction(mp.getElement("logoutButton"));
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://test.urbanicfarm.com/");
    }

    @Then("Navigate to Farmers Market Page and search for the product")
    public void navigateToFarmersMarketPageAndSearchForTheProduct() {
        MyUtils.clickWithActions(mp.getElement("urbanicFarmButton"));
        MyUtils.sendKeysFunction(mp.getElement("searchProductName"), addedProductName);
        MyUtils.clickWithActions(mp.getElement("searchButton"));
    }

    @Then("Add the relevant seller's product to the cart")
    public void AddTheRelevantSellerSProductToTheCart() {
        String cartProductInfo = addedProductName + "\n\n$" + addedProductPrice ;
        String siraNo = MyUtils.getElementsAttributeNo(pp.getElementList("foundProducts"), "outerText", cartProductInfo);
        if (!(siraNo == null)) {
            int sira = Integer.parseInt(siraNo);
            MyUtils.sendKeysFunction(pp.getElementList("inputProductQuantity").get(sira),addedProductStock);
            MyUtils.clickWithActions(pp.getElementList("addToCartButtons").get(sira));
            MyUtils.waitUntilVisible(pp.getElement("addingToBasketMessage"));
        }
    }

    @Then("Go to Cart and verify the purchase information to make payment.")
    public void goToCartAndVerifyThePurchaseInformationToMakePayment() {
        String currentWindow = Driver.getDriver().getWindowHandle();
        MyUtils.clickWithActions(mp.getElement("goToCartButton"));
        MyUtils.waitForLoadingPage();
        MyUtils.clickWithActions(mp.getElement("proceedToCheckoutButton"));
        MyUtils.waitForLoadingPage();
        MyUtils.clickWithActions(mp.getElement("nextButton"));
        MyUtils.waitForLoadingPage();
        MyUtils.clickWithActions(mp.getElement("nextButton"));
        MyUtils.waitForLoadingPage();
        MyUtils.clickWithActions(mp.getElement("goToPaymentButton"));
        for (int i = 0; currentWindow == Driver.getDriver().getWindowHandle(); i++) {
            MyUtils.waitFor(3000);
        }
        List<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        MyUtils.waitFor(10000);

    }

    @Then("Pay for the product with PayPal")
    public void payForTheProductWithPayPal() {
        String ppEmail = ConfigurationReader.getProperty("ppLoginEmail");
        String ppPassword = ConfigurationReader.getProperty("ppLoginPassword");

        List<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windowHandles.get(1));

        MyUtils.clickWithActions(payp.getElement("payPalGoButton"));

        payp.getElement("payPalEmail").clear();
        MyUtils.sendKeysWithActions(payp.getElement("payPalEmail"), ppEmail);
        payp.getElement("payPalPassword").clear();
        MyUtils.sendKeysWithActions(payp.getElement("payPalPassword"), ppPassword);
        MyUtils.clickWithActions(payp.getElement("payPalLoginButton"));
        MyUtils.clickWithActions(payp.getElement("payPalPaymentButton"));

        Driver.getDriver().switchTo().window(windowHandles.get(0));
    }

    @Then("Verify that the payment is successful")
    public void verifyThatThePaymentIsSuccessful() {
        MyUtils.waitUntilVisible(mp.getElement("VerifyPaymentMessage"));
        Assert.assertTrue(mp.getElement("VerifyPaymentMessage").getText().toLowerCase().contains("success"));
    }

    @And("Verify that the product is available in the Orders section")
    public void verifyThatTheProductIsAvailableInTheOrdersSection() {
        MyUtils.clickFunction(mp.getElement("viewOrderDetailsButton"));
        myBlock = addedProductName+"\nUnit Price: $"
                 +addedProductPrice+".00\nQty: "
                 +addedProductStock+"lb\n";
        String siraNo = MyUtils.getElementsAttributeNo(op.getElementList("foundOrdersList"), "outerText", myBlock);

        if (!(siraNo == null)) {
            int sira = Integer.parseInt(siraNo);
            orderedProductNo = MyUtils.getOrderNo(op.getElementList("orderNoList").get(sira).getAttribute("textContent"));
        }
        Assert.assertTrue(orderedProductNo!=null);
    }


    @Then("Go to Sold items page and verify the product is available")
    public void goToSoldItemsPageAndVerifyTheProductIsAvailable() {
            MyUtils.clickFunction(mp.getElement("sideBarSoldItemsButton"));

            String siraNo = MyUtils.getElementsAttributeNo(sip.getElementList("soldItemsList"), "outerText", myBlock);
            if (!(siraNo == null)) {
                int sira = Integer.parseInt(siraNo);
                MyUtils.scrollToElement(sip.getElementList("soldItemsList").get(sira));
                soldProductNo = sip.getElementList("orderNoList").get(sira).getAttribute("textContent");
                System.out.println("soldProductNo = " + soldProductNo);
            }
            Assert.assertNotEquals(soldProductNo, null);
        }

    @And("Verify that the order status is {string} for the buyer")
    public void verifyThatTheOrderStatusIsForTheBuyer(String status) {
        String siraNo = MyUtils.getElementsAttributeNo(op.getElementList("orderNoList"),
                                                "textContent", orderedProductNo);
        int sira = Integer.parseInt(siraNo);
        MyUtils.scrollToElement(op.getElementList("orderNoList").get(sira));

        String inProgresColor = MyUtils.getElementsCssValue(op.getElementList("inProgressList"),"background-color").get(sira);
        String onDeliveryColor = MyUtils.getElementsCssValue(op.getElementList("onDeliveryList"),"background-color").get(sira);
        String completedColor = MyUtils.getElementsCssValue(op.getElementList("completedList"),"background-color").get(sira);

        switch (status) {
            case "In Progress":
                Assert.assertNotEquals(inProgresColor, "rgba(255, 255, 255, 1)");
                Assert.assertEquals(onDeliveryColor, "rgba(255, 255, 255, 1)");
                Assert.assertEquals(completedColor, "rgba(255, 255, 255, 1)"); break;
            case "On Delivery":
                Assert.assertEquals(inProgresColor, "rgba(255, 255, 255, 1)");
                Assert.assertNotEquals(onDeliveryColor, "rgba(255, 255, 255, 1)");
                Assert.assertEquals(completedColor, "rgba(255, 255, 255, 1)"); break;
            case "Completed":
                Assert.assertEquals(inProgresColor, "rgba(255, 255, 255, 1)");
                Assert.assertEquals(onDeliveryColor, "rgba(255, 255, 255, 1)");
                Assert.assertNotEquals(completedColor, "rgba(255, 255, 255, 1)"); break;
        }

    }

    @And("Verify that the order status is {string} for the seller")
    public void verifyThatTheOrderStatusIsForTheSeller(String status) {
        String soldStatus = null;
        String soldStatusNo = null;

        String siraNo = MyUtils.getElementsAttributeNo(sip.getElementList("soldItemsList"), "outerText", myBlock);
        if (!(siraNo == null)) {
            int sira = Integer.parseInt(siraNo);
            MyUtils.scrollToElement(sip.getElementList("soldItemsList").get(sira));
            soldStatusNo = sip.getElementList("soldItemsStatusList").get(sira).getAttribute("selectedIndex");
        }
        switch (soldStatusNo){
            case "0" : soldStatus = "In Progress" ; break;
            case "1" : soldStatus = "On Delivery" ; break;
            case "2" : soldStatus = "Completed" ; break;
            }
        Assert.assertEquals(soldStatus, status);
    }

    @And("Change order status to {string}")
    public void changeOrderStatusTo(String newStatus) {
        String siraNo = MyUtils.getElementsAttributeNo(sip.getElementList("soldItemsList"), "outerText", myBlock);
        if (!(siraNo == null)) {
            int sira = Integer.parseInt(siraNo);

            WebElement soldProductDropDown = sip.getElementList("soldItemsStatusList").get(sira);
            Select select = new Select(soldProductDropDown);

            switch (newStatus){
                case "In Progress" : select.selectByIndex(0);  break;
                case "On Delivery" :  select.selectByIndex(1);  break;
                case "Completed" :  select.selectByIndex(2);  break;
            }
        }
    }

    @And("Go to Orders page")
    public void goToOrdersPage() {
        MyUtils.clickFunction(mp.getElement("sideBarOrdersButton"));
    }

    @Then("Go to Sold items page")
    public void goToSoldItemsPage() {
        MyUtils.clickFunction(mp.getElement("sideBarSoldItemsButton"));
    }
}
