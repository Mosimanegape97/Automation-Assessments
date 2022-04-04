package webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchHotel {
    protected WebDriver driver;

    public SearchHotel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"location\"]")
    public WebElement location;
    @FindBy(xpath = "//*[@id=\"hotels\"]")
    public WebElement hotel;
    @FindBy(xpath = "//*[@id=\"room_type\"]")
    public WebElement room_type;

    @FindBy(xpath = "//*[@id=\"room_nos\"]")
    public WebElement room_nos;

    @FindBy(xpath = "//*[@id=\"datepick_in\"]")
    public WebElement datepick_in;

    @FindBy(xpath = "//*[@id=\"datepick_out\"]")
    public WebElement datepick_out;

    @FindBy(xpath = "//*[@id=\"adult_room\"]")
    public WebElement adult_room;

    @FindBy(xpath = "//*[@id=\"child_room\"]")
    public WebElement child_room;

    @FindBy(xpath = "//*[@id=\"Submit\"]")
    public WebElement btnSubmit;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    public WebElement btnContinue;

    @FindBy(xpath = "//*[@id=\"Reset\"]")
    public WebElement btnReset;


}


