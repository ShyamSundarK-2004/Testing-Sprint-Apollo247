<<<<<<< HEAD
# Apollo247 Lab Test Automation

## Overview

This branch contains a dedicated automation framework for the Lab Test module of Apollo247 built using Selenium with BDD (Cucumber).

It automates real-time workflows such as search, prescription upload, radiology requests, order filtering, and end-to-end booking.

The implementation validates UI behavior, business logic, and complete user flow specific to the Lab Test module.
=======
# Apollo247 Automation Framework

## Overview

This project is a comprehensive end-to-end automation framework for the Apollo247 platform built using Selenium with BDD (Cucumber).

It automates multiple real-world healthcare workflows across key modules including Doctor Consultation, Health Insurance, Pharmacy, Lab Tests, and Account Management.

The framework validates UI behavior, business logic, and complete user journeys to ensure application reliability and quality.
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

---

## Scope

<<<<<<< HEAD
* Lab Test module automation only
* Covers positive, negative, and end-to-end scenarios
* Validates UI behavior and workflows
* Supports data-driven testing using Scenario Outline
* Designed for scalability and maintainability
=======
* Multi-module automation framework
* Covers positive, negative, and end-to-end scenarios
* Validates UI behavior and business workflows
* Supports data-driven testing (Excel and BDD Scenario Outline)
* Designed for scalability, reusability, and maintainability
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

---

## Tech Stack

* Java
* Selenium WebDriver
* Cucumber (BDD)
* TestNG
* Maven
<<<<<<< HEAD
=======
* Apache POI (Excel handling)
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

---

## Framework Design

* Page Object Model (POM)
* Reusable Step Definitions
* Scenario Outline for data-driven testing
* Hooks for setup and teardown
* Explicit waits using WebDriverWait
<<<<<<< HEAD
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
=======
* Modular and scalable architecture

---

## Modules Covered

### 1. Doctor Consultation Module

* Doctor booking by speciality
* Filtering by price, experience, and language
* Rebooking appointments
* BMI calculator using Excel data
* Doctor description validation

Negative scenarios:

* Missing location validation
* Invalid speciality handling

---

### 2. Health Insurance Module

#### Overview

Automates the Health Insurance workflow including member selection, validations, filtering, and complete policy purchase.

#### Scenarios

1. Member Selection (Data-Driven)

* Gender selection
* Member types (Self, Husband, Mother, etc.)
* Age inputs
* Plan loading validation

2. Validation Scenario

* Attempt to proceed without selecting members
* Validate message: "Select minimum one adult"

3. Multiple Member Selection

* Select multiple members
* Validate family plan display

4. Filtering and Sorting

* Coverage filter (₹10–24 Lakh)
* Room rent selection
* Sort by premium
* Validate results

5. End-to-End Policy Purchase Flow

* Member Details
* Medical Questions
* Proposer Details
* KYC Upload
* Address Proof Upload
* Address Details
* Nominee Selection
* Bank Details
* Terms and Conditions
* Navigate to payment
* Validate policy summary

#### Key Features

* BDD Scenario Outline with Examples
* Positive and Negative coverage
* End-to-End automation
* Reusable Step Definitions
* Page Object Model design

---

### 3. Pharmacy Module

* Medicine search and add to cart
* Product navigation
* Shop by brand
* Cart quantity management
* Excel data-driven testing
* Negative search validation

---

### 4. Lab Test Module

* Search functionality (valid and invalid inputs)
* Prescription upload (positive and negative cases)
* Radiology workflow
* Order filtering
* End-to-End booking flow

---

### 5. Account Module

* Manage family members (Excel-driven)
* Appointments validation
* Membership validation (positive and negative)
* Notification preferences
* Help section validation
* Logout functionality
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

---

## Key Features

* BDD with Scenario and Scenario Outline
<<<<<<< HEAD
* Positive and Negative test coverage
* End-to-End workflow validation
* Reusable Step Definitions
* Page Object Model design
* Scalable automation structure
=======
* Multi-module automation coverage
* Data-driven testing using Excel
* Positive and Negative scenario coverage
* End-to-End workflow validation
* Reusable and maintainable framework
* Real-time user workflow simulation
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

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
<<<<<<< HEAD
@labTest  
@searchScenario  
@prescriptionScenario  
@radiologyScenario  
@myOrder  
@EndToEndScenario  
@smoke  
@sanity  
@regression  
=======
@smoke  
@regression  
@sanity  

@doctor  
@insurance  
@pharmacy  
@labTest  
@account  

@search  
@validation  
@negative  
@filter  
@sorting  
@cart  
@policyPurchase  
@appointments  
@membership  
@notifications  
@logout  
@bmi  
@rebook  
@endToEnd  
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566
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

<<<<<<< HEAD
* Screenshots captured for important steps
* Can be integrated with:

  * Extent Reports
  * Cucumber Reports
=======
* Screenshots captured for important steps and failures
* Can be integrated with:

  * Extent Reports
  * Allure Reports
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566

---

## Future Enhancements

* Parallel execution using TestNG
<<<<<<< HEAD
* Selenium Grid integration
* CI/CD pipeline (Jenkins or GitHub Actions)
* Advanced reporting dashboards

---

## Note

This branch is focused exclusively on Lab Test module automation and is part of a larger scalable automation framework.

---

## Author

Shyam Sundar K
=======
* Selenium Grid for cross-browser testing
* CI/CD integration (Jenkins or GitHub Actions)
* Advanced reporting dashboards
* Headless execution

---

## Authors

* Shyam Sundar K – Lab Test Module
* Gokul Anand S – Health Insurance Module
* Roshini Ravikumar – Pharmacy Module
* Priyanka G – Account Module
* Swetha B – Doctor Consultation Module
>>>>>>> eee68615b54d7b34187e8e1b4f34ffe7af325566
