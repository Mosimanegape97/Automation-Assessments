package webPageObects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class applyJob {
    protected WebDriver driver;
    public  applyJob(WebDriver driver ){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"wpjb-scroll\"]/div[1]/a")
    public WebElement btnApply;

    @FindBy(xpath = "//*[@id=\"applicant_name\"]")
    public WebElement txtFullName;
    @FindBy(xpath = "//*[@id=\"email\"]")
    public WebElement txtEmail;
    @FindBy(xpath = "//*[@id=\"phone\"]")
    public WebElement txtPhoneNum;


    @FindBy(xpath = "//*[@id=\"message\"]")
    public WebElement txtMessage;

    @FindBy(xpath = "//*[@id=\"wpjb_submit\"]")
    public WebElement btnSendApplication;


    @FindBy(xpath = "//*[@id=\"wpjb-apply-form\"]/fieldset[1]/div[5]/div/ul/li")
    public WebElement uploadErrMessage;

}
