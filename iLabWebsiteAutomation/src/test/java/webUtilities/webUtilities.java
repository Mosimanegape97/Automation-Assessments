package webUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class webUtilities {

    protected static WebDriver driver;

    //get and set method for the driver
    public static WebDriver getWebDriver() {
        return driver;
    }

    public void setWebDriver(WebDriver DriverTest) {
        this.driver = DriverTest;
    }

    //initialize the webDriver
    public WebDriver initializeWebDriver(String sBrowser) {
        switch (sBrowser.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                //initialise the driver
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                //initialise the driver
                driver = new FirefoxDriver();
                break;
            case "IE":
                WebDriverManager.chromiumdriver().setup();
                //initialise the driver
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        //waits for a specific condition to happen or not happen
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
    public  void navigateUrl(String url) {
        driver.get(url);
    }

    public  void closeBrowser(){
        driver.quit();
    }

}
