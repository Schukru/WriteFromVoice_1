package _Typer;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpeechTextPage {
    public SpeechTextPage() {
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
    @FindBy(css = "button[id='b-settings']")
    private WebElement settingsButton;
    @FindBy(css = "input[id='settings-cap-first-letter']")
    private WebElement firstLetterCheckBox;
    @FindBy(css = "button[id='settings-b-close']")
    private WebElement settingsCloseButton;
    @FindBy(css = "div[id='note']")
    private WebElement writtenText;
    @FindBy(css = "div[id='lang-name-display']")
    private WebElement chooseCountryButton;
    @FindBy(css = "div[class='lang-option'][data-country='Turkey']")
    private WebElement chooseTurkeyLine;
    @FindBy(css = "button[id='b-edit-cc']")
    private WebElement editButton;

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

        }
        return myElement;
    }

}
