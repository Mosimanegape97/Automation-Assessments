package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import reports.Reporting;
import webPageObects.applyJob;
import webPageObects.careersClick;
import webPageObects.currentOpenings;
import webPageObects.southAfricaPage;
import webUtilities.webActions;

import java.sql.ResultSet;
import java.util.Random;

public class webFuctions extends webActions {
    Reporting myReportsObject = new Reporting();

    public void clickCareer(WebDriver driver, ExtentTest test) {
        careersClick careerObject = new careersClick(driver);

        try {
            webActions.clickObject(careerObject.clickCareers, driver);
            String fileName = myReportsObject.captureScreenShot(driver);
            if (careerObject.careersImg.isDisplayed()) {
                //System.out.println("Successfully click careers link");
                test.pass("Successfully click careers link",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                //System.out.println("Unsuccessfully click careers link");
                test.fail("Unsuccessfully click careers link",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }

        } catch (Exception ex) {
            System.out.println("Cannot click careers link, Error: " + ex.getMessage());
        }
    }

    public void clickSouthAfricanLink(WebDriver driver ,ExtentTest test) {
        southAfricaPage mySAObject = new southAfricaPage(driver);
        try {
            webActions.clickObject(mySAObject.linkSouthAfrican, driver);
            String fileName = myReportsObject.captureScreenShot(driver);
            if (mySAObject.txtCurrentOpenings.isDisplayed()) {
                //System.out.println("Successfully clicked South African careers link");
                test.pass("Successfully clicked South African careers link", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                //System.out.println("Unsuccessfully clicked South African careers link");
                test.fail("Unsuccessfully clicked South African careers link",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }

        } catch (Exception ex) {
            System.out.println("Cannot click South Africa careers link, Error: " + ex.getMessage());
        }
    }

    public void clickBScLink(WebDriver driver, ExtentTest test) {
        currentOpenings myCurrentOpeningObject = new currentOpenings(driver);
        try {
            webActions.clickObject(myCurrentOpeningObject.clickBScJob, driver);
            String fileName = myReportsObject.captureScreenShot(driver);
            if (myCurrentOpeningObject.bscIntern.isDisplayed()) {
                //System.out.println("Successfully clicked Interns- BSC Computer Science, National Diploma  link");
                test.pass("Successfully clicked Interns- BSC Computer Science, National Diploma  link",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                //System.out.println("Unsuccessfully clicked Interns- BSC Computer Science, National Diploma  link");
                test.fail("Unsuccessfully clicked Interns- BSC Computer Science, National Diploma  link",MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }

        } catch (Exception ex) {
            System.out.println("Cannot click Interns- BSC Computer Science, National Diploma  link, Error: " + ex.getMessage());
        }
    }

    public void clickApply(WebDriver driver, ResultSet sqlDb,ExtentTest test) {
        applyJob myApplyObject = new applyJob(driver);
        try {
            webActions.clickObject(myApplyObject.btnApply, driver);
            //add a more nicer wait. like waiting for the button to be clicked
            Thread.sleep(5000);

            passData(myApplyObject.txtFullName, driver, sqlDb.getString("fullName"));
            passData(myApplyObject.txtEmail, driver, sqlDb.getString("email"));
            passData(myApplyObject.txtPhoneNum,driver,generatePhoneNum());
            passData(myApplyObject.txtMessage, driver, sqlDb.getString("reason"));

            clickObject(myApplyObject.btnSendApplication,driver);

            Thread.sleep(5000);
            String fileName = myReportsObject.captureScreenShot(driver);
            if (myApplyObject.uploadErrMessage.isDisplayed()) {
                //System.out.println("Successfully clicked apply button");
                test.pass("Successfully clicked apply button", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                //System.out.println("Unsuccessfully clicked apply button");
                test.fail("Unsuccessfully clicked apply button", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            }


        } catch (Exception ex) {
            System.out.println("Cannot click apply button, Error: " + ex.getMessage());
        }

    }

    public String generatePhoneNum(){
        int phoneNum1 =(int) ( (Math.random() * 99));//two digits
        int phoneNum2 =(int) ( (Math.random() * 999));//3 digits
        int phoneNum3 =(int) ( (Math.random() * 9999));//4 digits
        String sPhoneNum = 0 + Integer.toString(phoneNum1 )+" "+ phoneNum2 +" "+ phoneNum3;
        return sPhoneNum;
    }
}
