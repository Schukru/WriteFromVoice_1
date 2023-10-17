package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    public MainPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private WebElement myElement;


    @FindBy(css="div>a[href='/auth/login']")
    private WebElement loginButton;
    @FindBy(css="nav>a[href='/auth/logout']")
    private WebElement logoutButton;

    @FindBy(css="p[class='m-0 mr-3 text-capitalize h6']")
    private WebElement zipCodeControlText;
    @FindBy(xpath = "(//*[@id='Urban\\u0131cFarm'])[1]")
    private WebElement urbanicFarmButton;
    @FindBy(css = "input[id='mapSearchBox']")
    private WebElement searchProductName;
    @FindBy(css = "button[type='button'][aria-label='Close']")
    private WebElement alertCancelButton;
    @FindBy(css = "button[id='searchMapButton']")
    private WebElement searchButton;
    @FindBy(xpath = "//span[contains(text(), 'Change ZIP code')]")
    private WebElement changeZIPCodeButton;
    @FindBy(css="input[type='text']")
    private WebElement inputZipCode;
    @FindBy(xpath = "//button[contains(text(), 'go')]")
    private WebElement zipCodeButton;
    @FindBy(xpath = "//a[contains(text(), 'Your products')]")
    private WebElement sideBarProductButton;
    @FindBy(xpath = "//a[contains(text(), 'requested products')]")
    private WebElement sideBarRequestedProductButton;
    @FindBy(css = "span[class='col-6 col-sm-4 text-center mb-3 cursor-pointer text-secondary']>svg")
    private List<WebElement> hubList;
    @FindBy(css = "svg[hubuniquename]")
    private List<WebElement> productHubList;
    @FindBy(css = "datalist[id] option")
    private List<WebElement> dataList;
    @FindBy(xpath = "//a[contains(text(), 'Orders')]")
    private WebElement sideBarOrdersButton;
    @FindBy(xpath = "//a[contains(text(), 'Sold items')]")
    private WebElement sideBarSoldItemsButton;
    @FindBy(id = "selectProductName")
    private WebElement inputProductName;
    @FindBy(css = "input[name='price']")
    private WebElement inputProductPrice;
    @FindBy(css = "input[name='stock']")
    private WebElement inputProductStock;
    @FindBy(css = "select[class='form-control']")
    private WebElement inputProductUnit;
    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    private WebElement submitButton;
    @FindBy(css = "a[class='GoBack_goback__6sa4O']")
    private WebElement goBackButton;
    @FindBy(css = "div[class='Toastify__toast-body toastr_custom-toastr__iiU37']")
    private WebElement productAddedAlert;
    @FindBy(xpath = "(//div[@class='d-flex justify-content-center align-items-center'])[2]")
    private WebElement goToCartButton;
    @FindBy(xpath = "//button[contains(text(), 'PROCEED TO CHECKOUT')]")
    private WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//button[contains(text(), 'Next')]")
    private WebElement nextButton;
    @FindBy(xpath = "//button[contains(text(), 'Go To Payment')]")
    private WebElement goToPaymentButton;
    @FindBy(xpath = "//a[contains(text(), 'View order detail')]")
    private WebElement viewOrderDetailsButton;
    @FindBy(xpath = "//p[contains(text(), 'Payment successful')]")
    private WebElement VerifyPaymentMessage;
    @FindBy(css = "input[id='price']")
    private WebElement updateAddingProductPrice;
    @FindBy(css = "input[id='stock']")
    private WebElement updateAddingProductStock;
    @FindBy(css = "button[class='btn btn-success']")
    private WebElement updateAddingProductButton;
    @FindBy(css = "div[role='alert']")
    private WebElement updateAlert;

    // Liste elementler
    private List<WebElement> myElementList;
    @FindBy(xpath = "//span[@class = 'HubManagement_badge__2jPOb text-success']/..")
    private List<WebElement> hubNames;
    @FindBy(xpath = "//*[@id='__next']/div[4]/div[3]/div/div/div/div[2]/div[2]/div[5]/div/span[1]")
    private List<WebElement> hubPrices;
    @FindBy(xpath = "//*[@id='__next']/div[4]/div[3]/div/div/div/div[2]/div[2]/div[5]/div/span[2]")
    private List<WebElement> hubStocks;
    @FindBy(xpath ="//*[@id='__next']/div[4]/div[3]/div/div/div/div[2]/div[2]/div[5]/span")
    private List<WebElement> hubApproves;
    @FindBy(xpath="//div[@class='row p-0']//button[ contains(text(), 'Add to Cart')]")
    private List<WebElement> addToCartButtons;
    @FindBy(css="div[class='row p-0'] input[class='ProductCard_number__1yGnR']")
    private List<WebElement> inputProductQuantity;
    @FindBy(css="a[class='font-weight-bold text-secondary text-capitalize d-block text-truncate HubManagement_productTitle__2ke7R ']")
    private List<WebElement> currentProductsInHub;

    public WebElement getElement(String strElement){
        switch (strElement){
            case "loginButton": myElement = loginButton; break;
            case "logoutButton": myElement = logoutButton; break;
            case "zipCodeControlText" : myElement = zipCodeControlText; break;
            case "urbanicFarmButton" : myElement = urbanicFarmButton; break;
            case "searchProductName" : myElement = searchProductName; break;
            case "alertCancelButton" : myElement = alertCancelButton; break;
            case "searchButton" : myElement = searchButton; break;
            case "inputZipCode" : myElement = inputZipCode; break;
            case "zipCodeButton" : myElement = zipCodeButton; break;
            case "sideBarProductButton" : myElement = sideBarProductButton; break;
            case "sideBarRequestedProductButton" : myElement = sideBarRequestedProductButton; break;
            case "sideBarOrdersButton" : myElement = sideBarOrdersButton; break;
            case "sideBarSoldItemsButton" : myElement = sideBarSoldItemsButton; break;
            case "inputProductName" : myElement = inputProductName; break;
            case "inputProductPrice" : myElement = inputProductPrice; break;
            case "inputProductStock" : myElement = inputProductStock; break;
            case "submitButton" : myElement = submitButton; break;
            case "goBackButton" : myElement = goBackButton; break;
            case "productAddedAlert" : myElement = productAddedAlert; break;
            case "goToCartButton" : myElement = goToCartButton; break;
            case "proceedToCheckoutButton" : myElement = proceedToCheckoutButton; break;
            case "nextButton" : myElement = nextButton; break;
            case "goToPaymentButton" : myElement = goToPaymentButton; break;
            case "VerifyPaymentMessage" : myElement = VerifyPaymentMessage; break;
            case "viewOrderDetailsButton" : myElement = viewOrderDetailsButton; break;
            case "updateAddingProductPrice" : myElement = updateAddingProductPrice; break;
            case "updateAddingProductStock" : myElement = updateAddingProductStock; break;
            case "updateAddingProductButton" : myElement = updateAddingProductButton; break;
            case "updateAlert" : myElement = updateAlert; break;
            case "changeZIPCodeButton" : myElement = changeZIPCodeButton; break;

        }
        return myElement;
    }

    public List<WebElement> getElementList(String myElements){
        switch (myElements){
            case "hubList" : myElementList = hubList; break;
            case "productHubList" : myElementList = productHubList; break;
            case "dataList" : myElementList = dataList; break;
            case "hubNames" : myElementList = hubNames; break;
            case "hubPrices" : myElementList = hubPrices; break;
            case "hubStocks" : myElementList = hubStocks; break;
            case "hubApproves" : myElementList = hubApproves; break;
            case "addToCartButtons" : myElementList = addToCartButtons; break;
            case "inputProductQuantity" : myElementList = inputProductQuantity; break;
            case "currentProductsInHub" : myElementList = currentProductsInHub; break;
        }
        return myElementList;
    }


}
