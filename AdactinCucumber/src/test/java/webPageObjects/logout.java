package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class logout {

    protected static WebDriver driver;
    public logout(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this );
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }
    @FindBy(xpath = "//*[@id=\"logout\"]")
    public WebElement btnLogout;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[1]/table/tbody/tr/td/a")
    public WebElement logOutMessage;
}
