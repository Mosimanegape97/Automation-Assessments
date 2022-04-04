package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class deleteBooking {

    protected static WebDriver driver;
    public deleteBooking(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }

    @FindBy(xpath = "//*[@id=\"btn_id_580828\"]")
    public WebElement btnCancelOrder;

    @FindBy(xpath = "//*[@id=\"booked_form\"]/table/tbody/tr[3]/td/input[1]")
    public WebElement btnDelete;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")
    public WebElement logout;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr/td[1]/table/tbody/tr/td/a")
    public WebElement logOutMessage;
}
