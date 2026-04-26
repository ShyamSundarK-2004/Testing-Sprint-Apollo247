Apollo247 Pharmacy End-to-End Functional Testing
Overview
This project focuses on automating end-to-end functional testing of the Apollo247 Pharmacy platform using Selenium with BDD (Cucumber).
It covers multiple real-time user workflows including medicine search, cart management, product browsing, brand filtering, and data-driven validation scenarios.
Scope of This Module
End-to-End pharmacy workflow automation
Covers both positive and negative scenarios
Includes UI validation and cart workflow testing
Data-driven testing using Excel
Product search and category navigation validation
Tech Stack
Java
Selenium WebDriver
Cucumber (BDD)
TestNG
Maven
Apache POI (Excel Handling)
Test Scenarios Implemented
1. Search Medicine and Add to Cart
Navigate to Buy Medicines page
Close popup if displayed
Search medicine by name
Open cart
Validate searched medicine is present in cart
Sample Data Covered:
Dolo-650 Tablet
Crocin Tablet
Paracetamol
2. Apollo Products Navigation
Navigate to Apollo Products section
Select Personal Care category
Open Skin Care subcategory
Add first available product
Validate product added successfully
3. Shop By Brand - Volini
Navigate to Shop By Brand section
Select Volini brand
Apply Inflammation filter
Add first available Volini product
Validate product added successfully
4. Cart Quantity Management
Ensure product exists in cart
Open cart page
Update quantity using Data Table
Validate updated quantity successfully
Test Data:
Quantity: 3
5. Excel Data Driven Testing
Read medicine names from Excel file
Search medicines dynamically
Add products to cart
Validate medicines added successfully
File Used:
Apollo247_TestData.xlsx
6. Negative Test Scenarios
Invalid Medicine Search
Search using invalid medicine name
Validate no medicines displayed
Validate no results message shown
Validate user remains on search page
Sample Data:
xyzabc123medicine
Key Features
BDD with Scenario and Scenario Outline
Data-driven testing using Excel
Positive and Negative scenario coverage
Reusable Step Definitions
Page Object Model (POM) framework
Dynamic waits using WebDriverWait
Scalable automation structure
Feature File Highlights
Background
User launches browser and navigates to Apollo247 website
Tags Used
@SearchMedicine
@ApolloProducts
@VoliniViaShopByBrand
@CartManagement
@NegativeSearch
@ExcelData
How to Execute
Clone the repository
Open project in Eclipse / IntelliJ
Update dependencies using Maven
Run TestRunner class
Execute scenarios using TestNG
Future Enhancements
Parallel execution
Selenium Grid integration
Jenkins CI/CD pipeline
Extent Reports / Allure Reports
Cross-browser testing
Headless execution
Author
Roshini Ravikumar
