package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="input[name='email']")
    private WebElement loginEMail;

    @FindBy(css="input[name='password']")
    private WebElement loginPassword;

    @FindBy(css = "button[type='submit']")
    private WebElement loginSubmitButton;


    private WebElement myElement;
    public WebElement getElement(String strElement){
        switch (strElement) {
            case "loginEMail" : myElement = loginEMail; break;
            case "loginPassword" : myElement = loginPassword; break;
            case "loginSubmitButton" : myElement = loginSubmitButton; break;

        }
        return myElement;
    }

}
