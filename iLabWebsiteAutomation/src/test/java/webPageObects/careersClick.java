package webPageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class careersClick {

    protected WebDriver driver;

    public careersClick(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

//    @FindBy(xpath = "//*[@id=\"menu-item-1373\"]/a")
//    public WebElement clickCareers;
    @FindBy(xpath ="//*[@id=\"menu-item-1373\"]/a")
    public WebElement clickCareers;

    @FindBy(xpath ="/html/body/section/div[2]/div/div/div/div[1]/div[1]/div/div/div/figure/div/img")
    public WebElement careersImg;

}
