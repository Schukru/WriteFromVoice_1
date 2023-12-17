package _Typer;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpeechTextPage {
    public SpeechTextPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="div[id='mic']")
    private WebElement startStopButton;
    @FindBy(css="div[id='b-copy-all']")
    private WebElement copyButton;
    @FindBy(css = "div[id='b-note-new']")
    private WebElement clearTextButton;
    @FindBy(css = "div[id='b-cm-import']")
    private WebElement importButton;
    @FindBy(css = "div[id='b-settings']")
    private WebElement settingsButton;
    @FindBy(css = "div[id='capitalizeFirstLetterNewSentence']")
    private WebElement firstLetterCheckBox;
    @FindBy(xpath = "//div[@id='menu-settings']//div[@class='button close']")
    private WebElement settingsCloseButton;
    @FindBy(css = "div[id='textEditor']")
    private WebElement writtenText;
    @FindBy(css = "div[id='language-name-display']")
    private WebElement chooseCountryButton;
    @FindBy(xpath = "//div[.='Türkçe (Türkiye)']")
    private WebElement chooseTurkeyLine;
    @FindBy(css = "div[id='b-cm-edit-commands']")
    private WebElement editButton;
    @FindBy(css = "div[id='snackbar']")
    private WebElement importPopUp;

    private WebElement myElement;

    public WebElement getElement(String strElement){
        switch (strElement) {
            case "startStopButton" : myElement = startStopButton; break;
            case "copyButton" : myElement = copyButton; break;
            case "clearTextButton" : myElement = clearTextButton; break;
            case "importButton" : myElement = importButton; break;
            case "writtenText" : myElement = writtenText; break;
            case "chooseCountryButton" : myElement = chooseCountryButton; break;
            case "chooseTurkeyLine" : myElement = chooseTurkeyLine; break;
            case "editButton" : myElement = editButton; break;
            case "settingsButton" : myElement = settingsButton; break;
            case "settingsCloseButton" : myElement = settingsCloseButton; break;
            case "firstLetterCheckBox" : myElement = firstLetterCheckBox; break;
            case "importPopUp" : myElement = importPopUp; break;

        }
        return myElement;
    }

}
