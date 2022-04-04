package webTests;

import PageObjects.webFunctions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import dataStore.dataStore;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reports.reporting;
import webUtilities.webUtilities;

import java.sql.ResultSet;

public class bookHotelTest {
    public webUtilities web = new webUtilities();
    public webFunctions adactin = new webFunctions();
    String sUrl, sBrowser, sdataStore;
    ExtentReports reports;
    reporting myRepo = new reporting();
    public dataStore myDataObject = new dataStore();

    ResultSet sqldb = null;

    //parameters from the xml file

    @Parameters({"adactinURL", "Browser", "dataStore"})
    @BeforeTest
    public void beforeMethod(String sAdactinURL, String Browser, String dataStore) {


        sUrl = sAdactinURL;
        sBrowser = Browser;
        sdataStore = dataStore;
        web.setWebDriver(web.initializeWebDriver(Browser));
        reports = myRepo.initializeReports("reports/report.html");

    }

    @Test
    public void BookHotel() throws Exception {

        ExtentTest node = reports.createTest("Booking Hotel");
        node.assignAuthor("Mosimanegape Chokoe: G2");
        ExtentTest test = null;
        try {

            sqldb = myDataObject.ConnectAndQuerySql("jdbc:mysql://localhost:3306/Booking", "root", "123456", "Select * From Hotels");

            int iRow = myDataObject.rowCount( sqldb);
            web.navigateUrl(sUrl);
            for (int i = 1; i <=iRow; i++) {

                if (sqldb.next()) {
                    if (sdataStore.contains("SQL"))
                        test = node.createNode("Book a hotel" );
                    adactin.login(web.getWebDriver(),sqldb);
                    adactin.Search_Hotel(web.getWebDriver(), sqldb);
                    adactin.SelectHotel(web.getWebDriver());
                    adactin.BookHotel(web.getWebDriver(), sqldb);
                    adactin.orderNumGenerated(web.getWebDriver());
                    adactin.BookedItinerary(web.getWebDriver());


                    adactin.SearchOrderNumber(web.getWebDriver());
                    adactin.deleteSearchNumber(web.getWebDriver());
                    adactin.SearchOrderNumber(web.getWebDriver());
                    adactin.deleteConfirm(web.getWebDriver());
                    adactin.logoutHotel(web.getWebDriver());
                }

            }

            sqldb.close();

        } catch (Exception e) {
            System.out.println("Error during testing Adactin par, Error: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        web.getWebDriver().quit();
        reports.flush();
    }
}
