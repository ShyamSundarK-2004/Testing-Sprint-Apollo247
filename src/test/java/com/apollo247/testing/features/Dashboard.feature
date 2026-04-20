Feature: Apollo 247 Login and Module Navigation

  Background:
    When close the popup shown

  @smoke @login @lab
  Scenario: Login and navigate to Lab Tests
    When User logs in with mobile number "7639200473"
    Then User should be prompted for OTP verification
    And User clicks on "Buy Medicine" module
