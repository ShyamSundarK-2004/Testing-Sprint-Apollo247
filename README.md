# Apollo247 Lab Test Automation

## Overview

This branch contains a dedicated automation framework for the Lab Test module of Apollo247 built using Selenium with BDD (Cucumber).

It automates real-time workflows such as search, prescription upload, radiology requests, order filtering, and end-to-end booking.

The implementation validates UI behavior, business logic, and complete user flow specific to the Lab Test module.

---

## Scope

* Lab Test module automation only
* Covers positive, negative, and end-to-end scenarios
* Validates UI behavior and workflows
* Supports data-driven testing using Scenario Outline
* Designed for scalability and maintainability

---

## Tech Stack

* Java
* Selenium WebDriver
* Cucumber (BDD)
* TestNG
* Maven

---

## Framework Design

* Page Object Model (POM)
* Reusable Step Definitions
* Scenario Outline for data-driven testing
* Hooks for setup and teardown
* Explicit waits using WebDriverWait
* Modular and scalable design

---

## Module Covered

### Lab Test Module

#### Overview

Automates critical workflows of the Lab Test module including search, prescription upload, radiology flow, order filtering, and complete booking process.

---

## Scenarios Covered

### 1. Search Functionality

* Validate search with multiple inputs
* Handle valid and invalid scenarios
* Verify accurate results

---

### 2. Prescription Upload

#### Positive Case

* Upload valid prescription
* Verify proceed button is enabled

#### Negative Case

* Upload invalid prescription
* Validate error popup

---

### 3. Radiology Flow

* Navigate to radiology section
* Enter required details (city, hospital, date, test, file)
* Validate request call functionality

---

### 4. Order Filtering

* Select patient from dropdown
* Validate filtered orders

---

### 5. End-to-End Booking Flow

* Search and select lab test
* Add to cart
* Enter patient details (data-driven)
* Select slot and address
* Proceed to payment
* Validate payment page

---

## Key Features

* BDD with Scenario and Scenario Outline
* Positive and Negative test coverage
* End-to-End workflow validation
* Reusable Step Definitions
* Page Object Model design
* Scalable automation structure

---

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

---

## Tags Used

```
@labTest  
@searchScenario  
@prescriptionScenario  
@radiologyScenario  
@myOrder  
@EndToEndScenario  
@smoke  
@sanity  
@regression  
```

---

## Execution

### Using Maven

```
mvn clean test
```

### Using TestNG

* Run the TestRunner class
* Execute scenarios using tags

---

## Reporting

* Screenshots captured for important steps
* Can be integrated with:

  * Extent Reports
  * Cucumber Reports

---

## Future Enhancements

* Parallel execution using TestNG
* Selenium Grid integration
* CI/CD pipeline (Jenkins or GitHub Actions)
* Advanced reporting dashboards

---

## Note

This branch is focused exclusively on Lab Test module automation and is part of a larger scalable automation framework.

---

## Author

Shyam Sundar K
