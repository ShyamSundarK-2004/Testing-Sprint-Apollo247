Feature: Automation Testing on Lab Test Module

  Background:
    Given User is on Lab Tests page
    Then check user in on correct module

  @labTest @searchScenario
  Scenario Outline: Verify search functionality for lab tests
    When User searches for "<input>"
    Then validate search result for "<type>"

    Examples:
      | input    | type    |
      | CBC Test | valid   |
      | @@@@     | invalid |
      | 12333    | invalid |

  @labTest @prescriptionScenario
  Scenario: Verify booking lab test using correct prescription format
    When User clicks on book test using prescription
    And User uploads valid prescription
    Then verify  proceed button is enabled

  @labTest @prescriptionScenario
  Scenario: Verify booking lab test using wrong prescription file format
    When User clicks on book test using prescription
    And User uploads valid prescription
    Then verify invalid file message displayed and click on ok
    And verify proceed button is not enabled

  @labTest @radiologyScenario
  Scenario: Verify user can initiate radiology request successfully
    When User clicks on lab test search bar
    And User clicks on explore radiology option and switch to radiology tab
    Then User should be on radiology page
    When User enters radiology details
      | city      | hospital                   | date     | tests         | filePath                                               |
      | Bengaluru | Indiranagar- Apollo Clinic | April-15 | X-Ray,CT Scan | C:\\Users\\Shyam Sundar\\Documents\\prescription2.jpeg |
    Then User should see request call button is enabled

  @labTest @myOrder
  Scenario Outline: Verify orders are filtered correctly for each patient in dropdown
    Given User should be on orders page
    When User clicks on patient dropdown
    Then User select a name "<names>" and check orders

    Examples:
      | names  |
      | Shyam  |
      | Sundar |
