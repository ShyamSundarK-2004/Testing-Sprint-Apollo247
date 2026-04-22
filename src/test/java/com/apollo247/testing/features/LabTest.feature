Feature: Automation Testing on Lab Test Module

  # Common setup for all scenarios → avoids repetition
  Background:
    Given User is on Lab Tests page
    Then check user in on correct module

  # Data-driven search validation (valid + invalid inputs)
  @labTest @searchScenario
  Scenario Outline: Verify search functionality for lab tests
    When User searches for "<input>"
    Then validate search result for "<input>","<type>"

    Examples:
      | input     | type    |
      | CBC Test  | valid   |
      | PPBS Test | valid   |
      | @@@@      | invalid |
      | 12333     | invalid |

  # Positive scenario → valid file upload
  @labTest @prescriptionScenario
  Scenario: Verify booking lab test using correct prescription format
    When User clicks on book test using prescription
    And User uploads valid prescription
    Then verify proceed button is enabled

  # Negative scenario → invalid file format validation
  @labTest @prescriptionScenario
  Scenario: Verify booking lab test using wrong prescription file format
    When User clicks on book test using prescription
    And User uploads valid prescription
    Then verify invalid file message displayed and click on ok
    And verify proceed button is not enabled

  # End-to-end radiology request flow with test data
  @labTest @radiologyScenario
  Scenario: Verify user can initiate radiology request successfully
    When User clicks on lab test search bar
    And User clicks on explore radiology option and switch to radiology tab
    Then User should be on radiology page
    When User enters radiology details
      | city    | hospital                  | date     | tests         | filePath                                               |
      | Chennai | Anna Nagar- Apollo Clinic | April-15 | X-Ray,CT Scan | C:\\Users\\Shyam Sundar\\Documents\\prescription2.jpeg |
    Then User should see request call button is enabled

  # Validate filtering logic based on selected patient
  @labTest @myOrder
  Scenario Outline: Verify orders are filtered correctly for each patient in dropdown
    Given User should be on orders page
    When User clicks on patient dropdown
    Then User select a name "<names>" and check orders

    Examples:
      | names  |
      | Shyam  |
      | Sundar |

  # Full booking journey → from search to payment page
  @labTest @EndToEndScenario
  Scenario: Verify end-to-end lab test booking flow with Excel data
  # Search & Add to Cart
    When User searches for lab test "CBC Test"
    And User clicks on add button for the test
    Then Verify only one test is added to cart
  # Go to Cart / Patient Selection
    When User clicks on proceed to cart
    Then Patient selection panel should be displayed
  # Add Patient using Excel (DDT)
    When User reads patient details from Excel
    And User enters patient details and clicks on save
    Then Verify patient is added successfully
  # Slot Selection
    When User clicks on select slot
    And User selects available date
    And User selects suggested time slot
    Then Verify slot is selected
  # Address Selection
    When User clicks on add new address
    And User enters address details and confirms location
    Then Verify address is added successfully
  # Review & Payment Page
    When User clicks on review cart
    Then Verify correct test, patient, slot and address details are displayed
    When User clicks on proceed to pay
    Then Verify user is navigated to payment page
  # Final Validation
    Then Verify total amount and payment options are displayed
