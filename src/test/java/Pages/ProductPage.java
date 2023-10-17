package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    public ProductPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[contains(text(), 'added to your basket')]")
    private WebElement addingToBasketMessage;


    @FindBy(css="div[class='row p-0']")
    private List<WebElement> foundProducts;
    @FindBy(xpath="//div[@class='row p-0']//button[ contains(text(), 'Add to Cart')]")
    private List<WebElement> addToCartButtons;
    @FindBy(css="div[class='row p-0'] input[class='ProductCard_number__1yGnR']")
    private List<WebElement> inputProductQuantity;
    @FindBy(css="a[class='font-weight-bold text-secondary text-capitalize d-block text-truncate HubManagement_productTitle__2ke7R ']")
    private List<WebElement> currentProductsInHub;

    private WebElement myElement;
    public WebElement getElement(String strElement){
        switch (strElement){
            case "addingToBasketMessage" : myElement = addingToBasketMessage; break;

        }
        return myElement;
    }


    private List<WebElement> myElementList;
    public List<WebElement> getElementList(String strElement){
        switch (strElement) {
            case "foundProducts" : myElementList = foundProducts; break;
            case "addToCartButtons" : myElementList = addToCartButtons; break;
            case "inputProductQuantity" : myElementList = inputProductQuantity; break;
            case "currentProductsInHub" : myElementList = currentProductsInHub; break;

        }
        return myElementList;
    }

}
