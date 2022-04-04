package webUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class webActions {

    protected static WebDriver driver;

    public static void clickObject(WebElement element, WebDriver driver) {
        try {
            if (element.isDisplayed()) {
                //the wait, waits periodically,til it times out
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))//tries looking for the object for 1000 milliseconds
                        .ignoring(WebDriverException.class);//ignores exception on the webdriver
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
            }
        } catch (Exception ex) {
            System.out.println("Element not found, error: " + ex.getMessage());
        }
    }

    public static void passData(WebElement textBox, WebDriver driver, String data) {
        try {
            if (textBox.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(textBox));
                clearTextField(textBox);
                textBox.sendKeys(data);

            }
        } catch (Exception ex) {
            System.out.println("Element not found, error: " + ex.getMessage());
        }
    }

    public static void selectObject(WebElement selectElement, WebDriver driver, String selectBy, Object selectValue) {
        //call the select object
        Select selectObject = new Select(selectElement);
        try {
            if (selectElement.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(selectElement));
                //clear fields
                clearTextField(selectElement);
                switch (selectBy.toUpperCase()) {
                    case "INDEX":
                        selectObject.selectByIndex((Integer) selectValue);
                        break;
                    case "SELECTBYVISIBLETEXT":
                        selectObject.selectByVisibleText((String) selectValue);
                        break;
                    case "VALUE":
                        selectObject.selectByValue((String) selectValue);
                        break;

                }
            }
        } catch (Exception ex) {
            System.out.println("Element not found, error: " + ex.getMessage());
        }


    }
    public static void clearTextField (WebElement element){
        element.clear();
    }
}
