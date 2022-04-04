package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class loginAdactin {
    protected WebDriver driver;
    public loginAdactin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"username\"]")
    public WebElement txtUsernme;
    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement txtPassword  ;
    @FindBy(xpath = "//*[@id=\"login\"]")
    public WebElement btnLogin;
    @FindBy(xpath = "//*[@id=\"Submit\"]")
    public WebElement btnNext;

}
