package webTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import dataStore.dataStore;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.webFuctions;
import reports.Reporting;
import webUtilities.webUtilities;

import java.sql.ResultSet;

public class jobApplicationTest {
    public webUtilities web = new webUtilities();
    public webFuctions iLab = new webFuctions();
    String sUrl, sBrowser, sDataStore;
    public dataStore myDataStorage = new dataStore();
    ResultSet sqlDb = null;
    ExtentReports myReports;
    Reporting myRepo = new Reporting();
    //parameters from the xml file
    @Parameters({"iLabUrl","Browser","dataStore"})
    @BeforeTest
    public void beforeMethod(String siLabUrl,String Browser, String dataStore){
        sUrl = siLabUrl;
        sBrowser = Browser;
        sDataStore = dataStore;
        web.setWebDriver(web.initializeWebDriver(Browser));
        myReports = myRepo.initializeReports("reports/report.html");
    }

    @Test
    public void jobApplication (){
        ExtentTest test = myReports.createTest("iLab Appply for Job");
        test.assignAuthor("Mosimanegape  Chokoe");

        try{
            sqlDb = myDataStorage.ConnectAndQuerySql("jdbc:mysql://localhost:3306/jobapplications","root","123456","Select * From personalinformation");
            int iRow = myDataStorage.rowCount(sqlDb);
            web.navigateUrl(sUrl);
            for(int i = 0; i <=iRow;i++){

                if(sqlDb.next()){
                    if(sDataStore.contains("SQL")){
                        iLab.clickCareer(web.getWebDriver(),test);
                        iLab.clickSouthAfricanLink(web.getWebDriver(),test);
                        iLab.clickBScLink(web.getWebDriver(), test);
                        iLab.clickApply(web.getWebDriver(), sqlDb, test);
                        web.navigateUrl(sUrl);
                    }
                }

            }
            sqlDb.close();

        }catch (Exception ex){
            System.out.println("Error while testing, Error: "+ex.getMessage());
        }
    }
    @AfterTest
    public  void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        web.getWebDriver().quit();
        myReports.flush();


    }
}
