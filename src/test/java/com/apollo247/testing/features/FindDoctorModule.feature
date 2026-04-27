Feature: Apollo 247 End-to-End Functional Testing
Background:
When user clicks the find_Docter module
# Scenario 1: Doctor Booking

@Finddoctor @doctorBooking @smoke
Scenario: Book a hospital visit appointment
When user searches for speciality
| speciality |
| Andrology  | 
And user selects first available doctor
And user clicks on schedule appointment
And user enters phone number
| phone      |
| 9876543210 |
Then validate phone number and amount displayed


# Scenario 2: Filter Doctor and Book
@Finddoctor @filter @regression
Scenario: Filter the doctor and book appointment
  When User click the general Physician
  And user applies sorting as "Price - low to high"
  And user filters doctors by experience "6-10"
  And user filters doctors by language "English"
  And user clicks on first displayed docter
  And user clicks the view profile and write review "handled in calm way"
  Then verify the feedback


# Scenario 3: Rebook Appointment
@Finddoctor @rebook @regression
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
  And User clicks CALCULATE button
  Then BMI category should match expected value from excel

  
# Scenario 5: Docter description validation 
@Finddoctor @description @regression
Scenario Outline: Validate doctor description for multiple doctors
  When User selects location and specialization
  And User sorts by Most Liked
  And User opens doctor description "<doctorName>"
  Then Doctor description should be validated "<expectedDescription>"

Examples:
  | doctorName         | expectedDescription                                                                     |
  | Dr. S K Agarwal    | Dr. S K Agarwal has over 43 years of experience and is fluent in English, Hindi.        |
  | Dr. Pawan Sharma   | Dr. Pawan Sharma has over 8 years of experience and is fluent in English, Hindi.        |
  
# Scenario 6: Find Doctor Negative Scenarios
@Finddoctor @Negative
Scenario: Validate error when location is not selected
  When User selects speciality "Andrology"
  And User selects date "24"
  And User clicks on submit without location
  Then Pincode validation message should be displayed
@Finddoctor @Negative1
Scenario: Validate no doctors found for invalid speciality
When user searches for the  speciality
| speciality |
| Ayurveda Dermatology |
Then no doctors found message should be displayed
