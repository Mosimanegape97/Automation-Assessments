package StepDefinitions;

import PageObjects.webFunctions;
import dataStore.dataStore;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import webUtilities.webUtilities;

import java.io.IOException;
import java.sql.ResultSet;

public class Steps {
    webUtilities web = new webUtilities();
    webFunctions adactin = new webFunctions();
    dataStore data = new dataStore();
    //WebDriver driver;
    //ResultSet resultSet;
    @Before
    public void startUp() throws InterruptedException {
        web.initializeWebDriver("CHROME");
        web.navigateUrl("http://adactinhotelapp.com");
    }
    @Given("a user launches {string} and navigates to the adactin webpage {string}")
    public void a_user_launches_and_navigates_to_the_adactin_webpage(String string, String string2) throws InterruptedException {
        System.out.println("user is on the login page");
    }
    @When("a user enter {string} and {string} and clicks login")
    public void a_user_enter_and_and_clicks_login(String string, String string2) {
       try{
           ResultSet resultSet = data.ConnectAndQuerySql("jdbc:mysql://localhost:3306/booking","root","123456","Select * from login");
           int iRow = data.rowCount(resultSet);
           for(int i =0;i<=iRow;i++){
               if(resultSet.next()){
                   adactin.login(web.getWebDriver(),resultSet);
               }
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }
    @When("a user populates fields in the search page and clicks continue")
    public void a_user_populates_fields_in_the_search_page_and_clicks_continue() {

        try{
            ResultSet resultSet = data.ConnectAndQuerySql("jdbc:mysql://localhost:3306/booking","root","123456","Select * from hotels");
            int iRow = data.rowCount(resultSet);
            for(int i =0;i<=iRow;i++){
                if(resultSet.next()){
                    adactin.Search_Hotel(web.getWebDriver(),resultSet);
                }
            }
        }catch (Exception ex){
            System.out.println("Search was not successful error, " + ex.getMessage());
        }
    }
    @When("a user selects a hotel from the selection page and click continue")
    public void a_user_selects_a_hotel_from_the_selection_page_and_click_continue() throws InterruptedException {
        adactin.SelectHotel(web.getWebDriver());


    }
    @When("a user populates the book confirmation with valid data and clicks confirm")
    public void a_user_populates_the_book_confirmation_with_valid_data_and_clicks_confirm() {


        try{
            ResultSet resultSet = data.ConnectAndQuerySql("jdbc:mysql://localhost:3306/booking","root","123456","Select * from hotels");
            int iRow = data.rowCount(resultSet);
            for(int i =0;i<=iRow;i++){
                if(resultSet.next()){
                    adactin.BookHotel(web.getWebDriver(),resultSet);
                }
            }

        }catch (Exception ex){
            System.out.println("Booking was not successful error, " + ex.getMessage());
        }


    }
    @Then("an order is generated and the booking is successful")
    public void an_order_is_generated_and_the_booking_is_successful() throws IOException {
        adactin.orderNumGenerated(web.getWebDriver());
    }
//    @When("a user clicks logout")
//    public void a_user_clicks_logout() {
//
//    }

    @When("Clicks on booked Itinerary")
    public void clicks_on_booked_itinerary() {
        adactin.BookedItinerary(web.getWebDriver());

    }
    @When("enter order number for a recently booked existing order and clicks on search")
    public void enter_order_number_for_a_recently_booked_existing_order_and_clicks_on_search() throws IOException {
        adactin.SearchOrderNumber(web.getWebDriver());
    }
    @When("a booking record is displayed on the table, the user user selects the booking radio button")
    public void a_booking_record_is_displayed_on_the_table_the_user_user_selects_the_booking_radio_button() {
        adactin.deleteSearchNumber(web.getWebDriver());
    }
    @When("a user clicks on cancel selected")
    public void a_user_clicks_on_cancel_selected() {
        adactin.deleteConfirm(web.getWebDriver());
    }
//    @Then("a confirm cancel popup message is displayed")
//    public void a_confirm_cancel_popup_message_is_displayed() {
//        //adactin.Cancel(web.getWebDriver());
//
//    }
//    @When("a user clicks on ok")
//    public void a_user_clicks_on_ok() {
//        //adactin.ClicksOnPopupOk(web.getWebDriver());
//    }
//    @Then("booking is successful cancelled")
//    public void booking_is_successful_cancelled() {
//       // adactin.SuccessfullyCancelled(web.getWebDriver());
//    }
    @When("a user clicks logout")
    public void a_user_clicks_logout() {
        adactin.logoutHotel(web.getWebDriver());
        web.getWebDriver().quit();
    }

    @After
    public void tearDown() throws InterruptedException {
        try{
            adactin.logoutHotel(web.getWebDriver());
        }catch (Exception ex){
            Thread.sleep(3000);
            web.getWebDriver().quit();
        }
    }


}
