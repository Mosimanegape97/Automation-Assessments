package PageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.reporting;
import webPageObjects.*;
import webUtilities.webActions;

import java.io.*;
import java.sql.ResultSet;
import java.time.Duration;

public class webFunctions extends webActions {

    public void login(WebDriver driver, ResultSet resultSet) {
        loginAdactin loginObject = new loginAdactin(driver);

        try {

            passData(loginObject.txtUsernme, driver, resultSet.getString("Username"));
            passData(loginObject.txtPassword, driver, resultSet.getString("Password"));
            webActions.clickObject(loginObject.btnLogin, driver);

            WebElement next = loginObject.btnNext;

            boolean testLogin = next.isDisplayed();
            if (!testLogin) {
                System.out.println("Invalid Login details");
            } else {
                System.out.println("Login successful");

            }
        } catch (Exception e) {
            System.out.println("Login unsuccessful, Error: " + e.getMessage());
            Assert.fail();
        }

    }

    public void Search_Hotel(WebDriver driver, ResultSet sqlDb) {
        SearchHotel searchObject = new SearchHotel(driver);
        try {

            selectOject(searchObject.location, driver, "VALUE", sqlDb.getString("Location"));
            selectOject(searchObject.hotel, driver, "VALUE", sqlDb.getString("Hotels"));
            selectOject(searchObject.room_type, driver, "VALUE", sqlDb.getString("RoomType"));
            selectOject(searchObject.room_nos, driver, "SELECTBYVISIBLETEXT", sqlDb.getString("NumberOfRooms"));
            passData(searchObject.datepick_in, driver, sqlDb.getString("CheckInDate"));
            passData(searchObject.datepick_out, driver, sqlDb.getString("CheckOutDate"));
            selectOject(searchObject.adult_room, driver, "SELECTBYVISIBLETEXT", sqlDb.getString("AdultsPerRoom"));


            clickObject(searchObject.btnSubmit, driver);
            if (driver.getCurrentUrl().contains("SelectHotel")) {
                System.out.println("Hotel Search Successful");
            } else {
                System.out.println("Hotel Search  unsuccessful");
            }

        } catch (Exception e) {
            System.out.println("Error searching for a hotel, Error: " + e.getMessage());
        }

    }


    public void SelectHotel(WebDriver driver) {
        selectHotel mySelectObject = new selectHotel(driver);
        try {

            if(driver.getCurrentUrl().contains("SelectHotel")) {
                clickObject(mySelectObject.radioButtonClick, driver);
                clickObject(mySelectObject.btnContinue, driver);

                if (driver.getCurrentUrl().contains("SelectHotel")) {
                    System.out.println("Hotel Selection Successful");
                } else {
                    System.out.println("Hotel Selection unsuccessful");
                }
            }
        } catch (Exception ex) {
            System.out.println("Could not select a hotel" + ex.getMessage());
        }
    }

    public void BookHotel(WebDriver driver, ResultSet sqbDb) {
        bookHotel myBooKingOject = new bookHotel(driver);
        try {

            passData(myBooKingOject.firstName, driver, sqbDb.getString("FirstName"));
            passData(myBooKingOject.lastName, driver, sqbDb.getString("LastName"));
            passData(myBooKingOject.billingAddress, driver, sqbDb.getString("BillingAddress"));
            passData(myBooKingOject.creditCardNum, driver, sqbDb.getString("CreditCardNumber"));

            selectOject(myBooKingOject.creditCardType, driver, "SelectByVisibleText", sqbDb.getString("CreditCardType"));
            selectOject(myBooKingOject.ccExpMonth, driver, "SelectByVisibleText", sqbDb.getString("ExpiryDateMonth"));
            selectOject(myBooKingOject.ccExpYear, driver, "SelectByVisibleText", sqbDb.getString("ExpiryDateYear"));

            passData(myBooKingOject.ccCVVNum, driver, sqbDb.getString("CVVNumber"));
            clickObject(myBooKingOject.btnBookNow, driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(myBooKingOject.orderNum));

            String order = myBooKingOject.orderNum.getAttribute("value");

            if (myBooKingOject.orderNum.isDisplayed()) {
                System.out.println("Booking hotel successful" + order);
            } else {
                System.out.println("Booking hotel unsuccessful");

            }

        } catch (Exception e) {
            System.out.println("Error while booking hotel, Error: " + e.getMessage());
        }

    }

    public void orderNumGenerated(WebDriver driver) throws IOException {
        confirmBooking book = new confirmBooking(driver);
        String OrderNum = book.OrderNumber.getAttribute("value");
        if (book.OrderNumber.isDisplayed()) {
            System.out.println("Hotel was booked successful,OrderNumber: " + OrderNum);
        } else {
            System.out.println("Order NUmber was not generated");
            //Assert.fail("Order NUmber was not generated");
        }

        String order = OrderNum;
        FileOutputStream fos = new FileOutputStream("src/main/resources/orderNumber.txt");
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeUTF(order);
        outStream.close();
    }

    public void BookedItinerary(WebDriver driver) {
        itinerary item = new itinerary(driver);
        item.bookedItinerary.click();
    }

    public void SearchOrderNumber(WebDriver driver) throws IOException {
        searchOrder search = new searchOrder(driver);

        String result;
        FileInputStream fis = new FileInputStream("src/main/resources/orderNumber.txt");
        DataInputStream reader = new DataInputStream(fis);
        result = reader.readUTF();
        //System.out.println(result);
        reader.close();
        //assertEquals(OrderNum, result);
        String arg1 = result;

        passData(search.txtOrderID, driver, arg1);
        clickObject(search.btnSearchOrder, driver);
    }

    public void deleteSearchNumber(WebDriver driver){
        searchOrder searchObject = new searchOrder(driver);
        try{
            clickObject(searchObject.btnRadio,driver);
            clickObject(searchObject.btnDelete,driver);

            Thread.sleep(3000);
            driver.switchTo().alert().accept();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void deleteConfirm(WebDriver driver ){
        confirmDelete myDeleteObject = new confirmDelete(driver);
        try{
            if(myDeleteObject.DeleteConfirmation.isDisplayed()){
                System.out.println("Successfully deleted the booking");
            }else{
                System.out.print("Booking was not successfully deleted");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void deleteHotel(WebDriver driver, ExtentTest test) {
        deleteBooking myDeleteObj = new deleteBooking(driver);

        try {

            clickObject(myDeleteObj.btnCancelOrder, driver);
            clickObject(myDeleteObj.btnDelete, driver);
            Thread.sleep(5000);

            driver.switchTo().alert().accept();

            Thread.sleep(5000);


            if (myDeleteObj.logout.isSelected()) {
                System.out.println("Successful search of Itinerary ");
                //test.pass("Successful search of Itinerary ");
            } else {
                System.out.println("Unsuccessful search of Itinerary");
                //test.fail("Unsuccessful search of Itinerary");
            }
            clickObject(myDeleteObj.logout, driver);
            clickObject(myDeleteObj.logOutMessage, driver);

        } catch (Exception ex) {
            System.out.println("Error while search Itenerary: " + ex.getMessage());
        }
    }

    public void logoutHotel(WebDriver driver) {
        logout logoutObject = new logout(driver);

        try {
            clickObject(logoutObject.btnLogout, driver);
            //Thread.sleep(5000);

            WebElement logOutMessage = logoutObject.logOutMessage;
            String fileName = reporting.captureScreenShot(driver);
            if (logOutMessage.isDisplayed()) {
                System.out.println("Logout successful");
                //test.pass("Logout successful", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                System.out.println("Logout Unsuccessful");
                //test.fail("Logout Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

            }
            //logins in again

            clickObject(logoutObject.logOutMessage, driver);
        } catch (Exception e) {
            System.out.println("Error logging out, Error: " + e.getMessage());
        }
    }
}
