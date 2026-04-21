Feature: Apollo 247 End-to-End Functional Testing
Background:
When user clicks the find_Docter module
Then validate modules has beeen clicked
# Scenario 1: Doctor Booking
@doctorBooking @smoke   
Scenario: Book a hospital visit appointment
  When  user searches for "Andrology" specialist in "Chennai" on date "20"
  And user selects doctor "Dr. Amvrin Chatterjee"
  And user selects available slot
  And user adds new patient with details
  | firstName  | lastName    | gender | email                | year | month | day |
  | Babu       | Subramani   | Male   | babuindu18@gmail.com | 1983 | Jan   | 29  |
  Then booking details should be displayed


# Scenario 2: Filter Doctor and Book
@filter @regression
Scenario: Filter the doctor and book appointment
  When User click the general Physician
  And user applies sorting as "Price - low to high"
  And user filters doctors by experience "6-10"
  And user filters doctors by language "English"
  And user clicks on first displayed docter
  And select the slot and continue to book
  Then verify the booking details


# Scenario 3: Rebook Appointment
@rebook @regression
Scenario: Verify Book Appointment option after rebooking
  When User navigates to My Appointments and clicks View All
  And User clicks on Rebook for a doctor
  And User clicks Continue
  And User click ChangeBtn to Change the patient and click proceed
  Then User should see Book Appointment option


# Scenario 4: BMI Calculator (Excel Driven)
@bmi @dataDriven
Scenario: Verify BMI result for Female user using Excel data
  When User navigates to Health Tools page
  And User clicks CALCULATE for Body Mass Index
  And User selects gender as Female
  And User navigates to height input
  And User enters BMI data from excel
  Then BMI result should be displayed

  
# Scenario 5: Docter description validation 
@description @regression
Scenario Outline: Validate doctor description for multiple doctors
  When User selects location and specialization
  And User sorts by Most Liked
  And User opens doctor description  "<docterName>"
  Then Doctor description should be validated ithuku nan scenario  "<expectedDescription>"

Examples:
  | doctorName         | expectedDescription                                                                     |
  | Dr. Meenakshi N    | Dr. Meenakshi N has over 28 years of experience and is fluent in English, Hindi, Punjabi.|
  | Dr. Tripti Agrawal | Dr. Tripti Agrawal has over 25 years of experience and is fluent in English, Hindi.      |
  
# Scenario 6: Doctor Search Validation
 @Negative1
  Scenario: Verify validation message when mandatory fields are missing
    Given User is on Doctor Search page
    When User clicks on search without entering location
    Then Validation message should be displayed for required field

  @Negative
  Scenario: Verify message when no doctors are found
    Given User is on Doctor Search page
    When User searches with rare criteria "Ayurveda Dermatology"
    Then No doctors found message should be displayed