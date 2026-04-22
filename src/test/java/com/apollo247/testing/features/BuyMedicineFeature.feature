Feature: Apollo 247 Pharmacy Website Functional Testing

  As a user
  I want to use Apollo 247 website features
  So that I can search medicines, browse products and manage cart

  Background:
    Given User launches the browser
    And User navigates to Apollo 247 website

  # -------------------- Scenario 1 : Scenario Outline --------------------
  @SearchMedicine
  Scenario Outline: Search medicine and add to cart
    Given User is on Buy Medicines page
    When User closes the popup
    And User searches medicine "<MedicineName>"
    And User clicks cart icon
    Then Product "<MedicineName>" should be visible in cart

    Examples:
      | MedicineName      |
      | Dolo-650 Tablet   |
      | Crocin Tablet     |
      | Paracetamol       |

  # -------------------- Scenario 2 --------------------
  @ApolloProducts
  Scenario: Navigate to Apollo Products and add first Skin Care product
    Given User is on Buy Medicines page
    When User clicks Apollo Products link
    And User clicks Personal Care category
    And User clicks Skin Care category
    And User adds first product
    Then Product should be added successfully

  # -------------------- Scenario 3 --------------------
  @Volini
  Scenario: Filter Volini products by Inflammation and add product
    Given User navigates to Volini page
    When User clicks Inflammation filter
    And User adds first Volini product
    Then Volini product should be added successfully

  # -------------------- Scenario 4 : Data Table --------------------
  @CartManagement
  Scenario: Change cart product quantity using Data Table
    Given User has product in cart
    When User updates cart quantity using below data
      | Quantity |
      | 3        |
    Then Product quantity should be updated successfully

  # -------------------- Scenario 5 --------------------
  @EmptyCart
  Scenario: Verify empty cart page elements
    Given Cart page is empty
    When User clicks cart icon
    Then Empty cart message should be displayed
    And Cart item count should be zero
    And Continue Shopping button should be visible

  # -------------------- Scenario 6 : Excel --------------------
  @ExcelData
  Scenario: Add medicines from Excel file
    Given User is on Buy Medicines page
    When User adds medicines from Excel file "MedicineData.xlsx"
    Then Medicines should be added successfully