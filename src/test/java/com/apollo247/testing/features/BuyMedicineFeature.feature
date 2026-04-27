Feature: Apollo 247 Pharmacy Website Functional Testing

  As a user
  I want to use Apollo 247 website features
  So that I can search medicines, browse products and manage cart

  Background:
    Given User launches the browser
    And User navigates to Apollo 247 website
    When User closes the popup

  # -------------------- Scenario 1 --------------------
  @SearchMedicine
  Scenario Outline: Search medicine and add to cart
    Given User is on Buy Medicines page
    When User searches medicine "<MedicineName>"
    And User clicks cart icon
    Then Product "<MedicineName>" should be visible in cart

    Examples:
      | MedicineName |
      | Dolo-650     |
      | Crocin       |
      | Paracetamol  |

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
  @VoliniViaShopByBrand
  Scenario: Navigate to Volini via Shop By Brand and filter by Inflammation
    Given User is on Buy Medicines page
    When User navigates to Volini via Shop By Brand
    And User clicks Inflammation filter
    And User adds first Volini product
    Then Volini product should be added successfully

  # -------------------- Scenario 4 --------------------
  @CartManagement
  Scenario: Change cart product quantity using Data Table
    Given User has product in cart
    When User updates cart quantity using below data
      | Quantity |
      | 3        |
    Then Product quantity should be updated successfully

  # -------------------- Scenario 5 --------------------
  @NegativeSearch
  Scenario: Search with invalid medicine name
    When User searches medicine "xyzabc123medicine"
    Then No medicines should be displayed
    And No result message should be visible
    And User should remain on search page

  # -------------------- Scenario 6 --------------------
  @ExcelData
  Scenario: Add medicines from Excel file
    Given User is on Buy Medicines page
    When User adds medicines from Excel file "Apollo247_TestData.xlsx"
    Then Medicines should be added successfully