Feature: Apollo 247 Pharmacy Website Functional Testing

  As a user
  I want to use Apollo 247 website features
  So that I can search medicines, add products, manage cart, and browse Apollo products

  Background:
    Given User launches the browser
    And User navigates to Apollo 247 website

  # -------------------- Scenario 1 --------------------
  @SearchMedicine
  Scenario Outline: Search medicine and add to cart
    Given User is on Buy Medicines page
    When User closes the popup
    And User searches medicine "<MedicineName>"
    And User adds the medicine to cart
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

  # -------------------- Scenario 4 --------------------
@CartManagement
  Scenario: Change cart product quantity to three
    Given User has product in cart
    When User clicks cart icon
    And User changes quantity to 3
    Then Product quantity should be updated to 3

  # -------------------- Scenario 5 --------------------
@EmptyCart
  Scenario: Verify empty cart page elements
    Given Cart page is empty
    When User clicks cart icon
    And Cart page loads successfully
    Then Empty cart message should be displayed
    And Cart item count should be zero
    And Continue Shopping button should be visible

  # -------------------- Scenario 6 --------------------
@AddMultipleMedicines
  Scenario: Add multiple medicines using Data Table
    Given User is on Buy Medicines page
    When User adds medicines using below data
      | MedicineName    |
      | Dolo-650 Tablet |
      | Crocin Advance  |
      | Volini Gel      |
    Then All medicines should be added successfully