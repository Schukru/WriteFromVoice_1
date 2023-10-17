package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SQLPage {

    public SQLPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css="input[name='pma_username']")
    private WebElement userName;
    @FindBy(css="input[name='pma_password']")
    private WebElement userPassword;
    @FindBy(id = "input_go")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id='topmenu']/li[1]/a")
    private WebElement databaseButton;
    @FindBy(css = "a[title=\"'urbanicfarm' veritabanına atla\"]")
    private WebElement urbanicFarmDataButton;
    @FindBy(xpath = "//*[@id=\"topmenu\"]/li[2]/a")
    private WebElement sqlButton;
    @FindBy(xpath = "//*[@id=\"queryfieldscontainer\"]/div/div/div[1]/div/div[6]/div[1]/div/div/div/div[5]/div/pre")
    private WebElement queryLine;
    @FindBy(id = "button_submit_query")
    private WebElement submitQueryButton;
    @FindBy(xpath = "//*[@id=\"sqlqueryresultsouter\"]/div/div[1]")
    private WebElement queryResultText;

    private WebElement myElement;
    public WebElement getElement(String strElement){

        switch (strElement)
        {
            case "userName" : myElement = userName; break;
            case "userPassword" : myElement = userPassword; break;
            case "loginButton" : myElement = loginButton; break;
            case "databaseButton" : myElement = databaseButton; break;
            case "urbanicFarmDataButton" : myElement = urbanicFarmDataButton; break;
            case "sqlButton" : myElement = sqlButton; break;
            case "queryLine" : myElement = queryLine; break;
            case "submitQueryButton" : myElement = submitQueryButton; break;
            case "queryResultText" : myElement = queryResultText; break;

        }
        return myElement;
    }

}
