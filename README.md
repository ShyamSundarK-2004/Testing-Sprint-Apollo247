# Apollo247 Health Insurance Automation

## Overview
This project automates the Health Insurance module of Apollo247 using Selenium with BDD (Cucumber). It validates critical user flows such as member selection, validation handling, filtering, and end-to-end policy purchase, ensuring reliability and accuracy of the insurance journey.

## Scope
- Health Insurance module automation
- Covers positive, negative, and end-to-end scenarios
- Validates UI behavior, business rules, and workflows

## Tech Stack
- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven

## Test Scenarios Covered

### 1. Member Selection (Data-Driven)
Validate member selection with:
- Gender (Male/Female)
- Member types (Self, Husband, Mother, etc.)
- Age inputs
- Ensures plans load successfully for valid combinations

### 2. Validation Scenario
- Attempt to proceed without selecting members
- Verifies validation message: "Select minimum one adult"

### 3. Multiple Member Selection
- Select multiple members (Self, Husband, Father)
- Validate family insurance plans are displayed correctly

### 4. Filtering & Sorting
- Apply coverage filter (₹10–24 Lakh)
- Select room rent type
- Sort plans by premium
- Validate filtered and sorted results

### 5. End-to-End Policy Purchase Flow
Select member and view plans → Customize plan → Complete full policy workflow:
- Member Details
- Medical Questions
- Proposer Details
- KYC Upload
- Address Proof Upload
- Address Details
- Nominee Selection
- Bank Details
- Accept Terms & Conditions
- Navigate to payment page
- Validate policy summary

## Key Features
- BDD Scenario Outline with Examples
- Positive & Negative test coverage
- End-to-End workflow automation
- Reusable Step Definitions
- Page Object Model (POM) design
- Scalable test structure

## Project Structure
```
src
 ├── main
 │    ├── java
 │    │     └── pages
 │    └── resources
 ├── test
 │    ├── java
 │    │     └── stepdefinitions
 │    └── resources
 │          └── features
```

## Tags Used
- @smoke
- @insurance
- @memberSelection
- @negative
- @validation
- @regression
- @filtering
- @sorting
- @policyPurchase

## How to Execute

### Using Maven
```bash
mvn clean test
```

### Using TestNG Runner
Run the TestRunner class

## Reporting
- Screenshots captured for important steps
- Can be integrated with Extent Reports for advanced reporting

## Future Enhancements
- Selenium Grid for cross-browser testing
- CI/CD integration (Jenkins / GitHub Actions)
- Parallel execution
- Enhanced reporting with logs and screenshots

## Author
**Gokul Anand S** - Automation Engineer, Health Insurance Module
