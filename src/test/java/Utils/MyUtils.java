
package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {

    private static int timeout = 30;
    private static WebDriverWait wait;
    private static JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    private static Actions actions = new Actions(Driver.getDriver());


    public static void clickFunction(WebElement element){
        waitUntilClickable(element);
        element.click();
    }

    public static void sendKeysFunction(WebElement element, String value) {
        waitUntilVisible(element);
        element.clear();
        element.sendKeys(value);
        MyUtils.getDriverWait(Driver.getDriver())
                .until(ExpectedConditions.attributeToBe(element, "value", value));
    }

    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,0);");
        waitFor(2000);
    }
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
        waitFor(300);
    }
    public static void waitUntilClickable(WebElement element) {
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilClickable(By locator) {
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebDriverWait getDriverWait(WebDriver driver) {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        }
        return wait;
    }
    public static void waitUntilInvisible(WebElement element) {
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public static void waitUntilVisible(WebElement element) {
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForLoadingPage() {
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public static void alertFunction(){
        wait = MyUtils.getDriverWait(Driver.getDriver());
        wait.until(ExpectedConditions.alertIsPresent());
        Driver.getDriver().switchTo().alert().accept();
    }

    public static void waitFor(int miliSaniye) {
        try {
            Thread.sleep(miliSaniye );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getElementsAttribute(List<WebElement> list, String attribute) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getAttribute(attribute).isEmpty()) {
                elemTexts.add(el.getAttribute(attribute));
            }
        }
        return elemTexts;
    }
    public static List<String> getElementsCssValue(List<WebElement> list, String cssValue) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getCssValue(cssValue).isEmpty()) {
                elemTexts.add(el.getCssValue(cssValue));
            }
        }
        return elemTexts;
    }
    public static String getElementsAttributeNo(List<WebElement> list, String attribute, String value) {
        waitFor(1000);
        String strNo = null;
        for (int i=0; i< list.size(); i++) {
            if (list.get(i).getAttribute(attribute).contains(value)){
                strNo = "" + i;
            };
        }
        return strNo;
    }
    public static String getElementText(WebElement element) {
        waitUntilVisible(element);
        return element.getText();
    }
    public static String getElementMultiText(WebElement element) {
        waitUntilVisible(element);
        return !element.getText().isEmpty() ? element.getText()
                : !element.getAttribute("innerText").isEmpty() ? element.getAttribute("innerText")
                : !element.getAttribute("value").isEmpty() ? element.getAttribute("value")
                : !element.getAttribute("title").isEmpty() ? element.getAttribute("value") : "";
    }
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    public static void clickWithJS(WebElement element) {
        scrollToElement(element);
        js .executeScript("arguments[0].click();", element);
        waitFor(300);
    }

    public static void clickWithActions(WebElement element) {
        scrollToElement(element); // elemente scroll yap
        hoverToElement(element);
        actions.moveToElement(element).click().perform();
    }

    public static void hoverToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public static void sendKeysWithActions(WebElement element, String value) {
        actions.sendKeys(element,value).perform();
    }

    public static String getOrderNo(String text){
        return text.substring((text.indexOf("#"))+1, (text.indexOf("#"))+18);
    }
    public static boolean isAnyStringInText(String text, String... stringsToCheck) {
        for (String str : stringsToCheck) {
            if (text.contains(str)) {
                return true;
            }
        }
        return false;
    }

}
