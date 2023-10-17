package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class _SpeechTextPage {
    public _SpeechTextPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="button[id='mic']")
    private WebElement startStopButton;

    @FindBy(css="button[id='b-copy']']")
    private WebElement copyButton;

    @FindBy(css = "button[id='b-delete']")
    private WebElement clearTextButton;

    @FindBy(css = "button[id='b-import-cc']")
    private WebElement importButton;

    @FindBy(css = "div[id='note']")
    private WebElement writtenText;

    private WebElement myElement;
    public WebElement getElement(String strElement){
        switch (strElement) {
            case "startStopButton" : myElement = startStopButton; break;
            case "copyButton" : myElement = copyButton; break;
            case "clearTextButton" : myElement = clearTextButton; break;
            case "importButton" : myElement = importButton; break;
            case "writtenText" : myElement = writtenText; break;

        }
        return myElement;
    }

}
