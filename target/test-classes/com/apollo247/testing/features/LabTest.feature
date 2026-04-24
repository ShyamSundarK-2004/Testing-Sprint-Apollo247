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
      | input    | type    |
      | CBC Test | valid   |
      | @@@@     | invalid |
      | 12333    | invalid |

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
    And verify wrong file uploaded popup shown

  # End-to-end radiology request flow with test data
  @labTest @radiologyScenario
  Scenario: Verify user can initiate radiology request successfully
    When User clicks on lab test search bar
    And User clicks on explore radiology option and switch to radiology tab
    Then User should be on radiology page
    When User enters radiology details
      | city    | hospital                  | date     | tests         | filePath                                               |
      | Chennai | Anna Nagar- Apollo Clinic | April-25 | X-Ray,CT Scan | C:\\Users\\Shyam Sundar\\Documents\\prescription2.jpeg |
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
  Scenario Outline: Verify end-to-end lab test booking flow
    When User searches for a test and selects test "<TestName>"
    And User adds test to cart
    Then Verify "<TestName>" is added to the cart
    When User enters patient details "<row>"
    And User selects slot and address
    When User proceeds to payment
    Then Verify payment page is displayed

    Examples:
      | TestName  | row |
      | CBC Test  | 1   |
      | PPBS Test | 2   |
