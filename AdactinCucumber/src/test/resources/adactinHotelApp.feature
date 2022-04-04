Feature: Book Hotel Feature

  Background: user logins
    Given a user launches "chrome" and navigates to the adactin webpage "http://adactinhotelapp.com"
    When a user enter "Mosimanegape27" and "514W6D" and clicks login

  Scenario: Successful booking of a hotel
    And a user populates fields in the search page and clicks continue
    And a user selects a hotel from the selection page and click continue
    And a user populates the book confirmation with valid data and clicks confirm
    Then an order is generated and the booking is successful
    When a user clicks logout

  Scenario: Delete an existing booking
    And Clicks on booked Itinerary
    When enter order number for a recently booked existing order and clicks on search
    When a booking record is displayed on the table, the user user selects the booking radio button
    And a user clicks on cancel selected
    When a user clicks logout




