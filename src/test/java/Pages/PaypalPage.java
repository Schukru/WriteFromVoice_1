package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaypalPage {

    public PaypalPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="button[id='acceptAllButton']")
    private WebElement acceptButton;
    @FindBy(xpath="//button[@type='button'][contains(text(), 'Log In')]")
    private WebElement payPalGoButton;
    @FindBy(css="input[name='login_email']")
    private WebElement payPalEmail;
    @FindBy(css="input[name='login_password']")
    private WebElement payPalPassword;
    @FindBy(css="button[id='btnLogin']")
    private WebElement payPalLoginButton;
    @FindBy(css="button[id='payment-submit-btn']")
    private WebElement payPalPaymentButton;

    private WebElement myElement;
    public WebElement getElement(String strElement){
        switch (strElement) {
            case "acceptButton" : myElement = acceptButton; break;
            case "payPalGoButton" : myElement = payPalGoButton; break;
            case "payPalEmail" : myElement = payPalEmail; break;
            case "payPalPassword" : myElement = payPalPassword; break;
            case "payPalLoginButton" : myElement = payPalLoginButton; break;
            case "payPalPaymentButton" : myElement = payPalPaymentButton; break;


        }
        return myElement;
    }

}


