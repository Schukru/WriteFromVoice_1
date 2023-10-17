package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SoldItemsPage {
    public SoldItemsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    private List<WebElement> myElementList;
    @FindBy(css ="div[class='card col-12 shadow-sm bg-white rounded border-success text-capitalize mb-1 mr-1']")
    private List<WebElement> soldItemsList;
    @FindBy(css ="select[class='cursor-pointer shadow-sm px-3 mb-5 bg-white rounded h6']")
    private List<WebElement> soldItemsStatusList;
    @FindBy(css ="p[class='mr-2']>select")
    private List<WebElement> dropDownMenuList;
    @FindBy(css ="span[class='h6 d-block']>span[class='text-muted']")
    private List<WebElement> orderNoList;

    public List<WebElement> getElementList(String myElements){
        switch (myElements){
            case "soldItemsList" : myElementList = soldItemsList; break;
            case "soldItemsStatusList" : myElementList = soldItemsStatusList; break;
            case "dropDownMenuList" : myElementList = dropDownMenuList; break;
            case "orderNoList" : myElementList = orderNoList; break;
        }
        return myElementList;
    }
}
