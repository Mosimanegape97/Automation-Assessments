package webUtilities;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class webActions {
    protected static WebDriver driver;

    public static void clickObject(WebElement element, WebDriver driver) {
        try {
            if (element.isDisplayed()) {
                //the wait, waits periodically, tell it the time out
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))//try looking for the object for 1000 milliseconds
                        .ignoring(WebDriverException.class);//ignores exception on the webdriver
                wait.until(ExpectedConditions.elementToBeClickable(element)); //waits until I can click the element
                element.click();
            }
        } catch (Exception e) {
            System.out.println(element + "Not found");
        }
    }

    public static void selectOject(WebElement selElement, WebDriver driver, String selectBy, Object selectValue) {
        Select mySelect = new Select(selElement);
        try {
            if (selElement.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofSeconds(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(selElement));
                //selElement.clear();
                switch (selectBy.toUpperCase()) {
                    case "INDEX":
                        mySelect.selectByIndex((Integer) selectValue);
                        break;
                    case "SELECTBYVISIBLETEXT":
                        mySelect.selectByVisibleText((String) selectValue);
                        break;
                    case "VALUE":
                        mySelect.selectByValue((String) selectValue);
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+ "Not found");
        }
    }

    public static void passData(WebElement textBox, WebDriver driver, String data) {
        try {
            if (textBox.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofSeconds(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(textBox));
                textBox.clear();
                textBox.sendKeys(data);
            }
        } catch (Exception e) {
            System.out.println(textBox + "Not found");
        }

    }

    public String getElementValue(WebElement e) {
        String sValue = null;
        if (e.isDisplayed()) {
            sValue = e.getAttribute("value");
        }
        return sValue;
    }

    public void clearTextField(WebElement element) {
        element.clear();
    }

    public boolean checkIfElementIsDisplayed(WebElement element, WebDriver driver) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;

    }

    public static void dropDownHandle(WebElement element, WebDriver driver,String selectBy) {
        Select sel = new Select(element);
        try {
            if (element.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofSeconds(1))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                sel.selectByValue(selectBy);
//                switch (selectBy.toUpperCase()){
//                    case "INDEX":
//                        sel.selectByIndex((Integer) selectValue);
//                        break;
//                    case "VISIBLETEXT":
//                        sel.selectByVisibleText((String) selectValue);
//                        break;
//                    case "VALUE":
//                        sel.selectByValue((String) selectValue);
//                        break;
//                }
            }

        } catch (Exception ex) {
            System.out.println("Error while passing data" + ex.getMessage());
        }
    }
}


