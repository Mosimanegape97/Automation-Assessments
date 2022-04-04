package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class bookHotel {

    protected static WebDriver driver;
    public bookHotel(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,3000), this);
    }
    @FindBy(xpath = "//*[@id=\"first_name\"]")
    public WebElement firstName;

    @FindBy(xpath = "//*[@id=\"last_name\"]")
    public WebElement lastName;

    @FindBy(xpath = "//*[@id=\"room_type\"]")
    public WebElement room_type;

    @FindBy(xpath = "//*[@id=\"address\"]")
    public WebElement billingAddress;

    @FindBy(xpath = "//*[@id=\"cc_num\"]")
    public WebElement creditCardNum;

    @FindBy(xpath = "//*[@id=\"cc_type\"]")
    public WebElement creditCardType;

    @FindBy(xpath = "//*[@id=\"cc_exp_month\"]")
    public WebElement ccExpMonth
            ;
    @FindBy(xpath = "//*[@id=\"cc_exp_year\"]")
    public WebElement ccExpYear;
    @FindBy(xpath = "//*[@id=\"cc_cvv\"]")
    public WebElement ccCVVNum;

    @FindBy(xpath = "//*[@id=\"book_now\"]")
    public WebElement btnBookNow;
    @FindBy(xpath = "//*[@id=\"cancel\"]")
    public WebElement btnCancel;
    @FindBy(xpath = "//*[@id=\"order_no\"]")
    public WebElement orderNum;

    @FindBy (xpath = "//*[@id=\"my_itinerary\"]")
    public WebElement btnItenerary;

}
