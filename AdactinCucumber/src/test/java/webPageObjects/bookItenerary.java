package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class bookItenerary {

    protected static WebDriver driver;
    public bookItenerary(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);
    }

    @FindBy(xpath = "//*[@id=\"booked_form\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/input")
    public WebElement selectHotelCheckBox;

    @FindBy(xpath = "//*[@id=\"btn_id_537959\"]")
    public  WebElement btnCancelSelected;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")
    public WebElement logout;
}
