package webUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class webUtilities {
    //static so we dont lose it
    protected static WebDriver driver;

    //get method
    public static WebDriver getWebDriver() {
        return driver;
    }

    //set method
    public void setWebDriver(WebDriver DriverTest) {
        this.driver = DriverTest;
    }

    //initialize the webDriver| with selenium you initialize the driver
    public WebDriver initializeWebDriver(String sBrowser) {
        switch (sBrowser.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();//look for the right driver for your browser version
                //initialise the driver
                driver = new ChromeDriver();
                //max the window
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "IE":
                WebDriverManager.chromiumdriver().setup();
                driver = new EdgeDriver();
                break;
        }
        driver.manage().window().maximize();
        //waits for a specific condition to happen or not happen
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;


    }
    public  void navigateUrl(String URL) throws InterruptedException {
        driver.get(URL);
        //Thread.sleep(2000);
    }
}
