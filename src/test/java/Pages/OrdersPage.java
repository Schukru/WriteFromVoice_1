package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage {
    public OrdersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    private WebElement myElement;
    @FindBy(css="nav>a[href='/auth/logout']")
    private WebElement logoutButton;


    private List<WebElement> myElementList;
    @FindBy(css ="article[class='d-flex justify-content-between align-items-center p-0  card-body ']")
    private List<WebElement> foundOrdersList;
    @FindBy(css ="section[class='card mb-2']>header")
    private List<WebElement> orderNoList;
    @FindBy(xpath ="//div[@type='button'][contains(text(), 'In Progress')]")
    private List<WebElement> inProgressList;
    @FindBy(xpath ="//div[@type='button'][contains(text(), 'On Delivery')]")
    private List<WebElement> onDeliveryList;
    @FindBy(xpath ="//div[@type='button'][contains(text(), 'Completed')]")
    private List<WebElement> completedList;


    public WebElement getElement(String strElement){
        switch (strElement){
            case "logoutButton" : myElement = logoutButton; break;

        }
        return myElement;
    }
    public List<WebElement> getElementList(String myElements){
        switch (myElements){
            case "foundOrdersList" : myElementList = foundOrdersList; break;
            case "orderNoList" : myElementList = orderNoList; break;
            case "inProgressList" : myElementList = inProgressList; break;
            case "onDeliveryList" : myElementList = onDeliveryList; break;
            case "completedList" : myElementList = completedList; break;

        }
        return myElementList;
    }

}
