# 🚀 Apollo247 Automation Framework

[![Java](https://img.shields.io/badge/Java-11%2B-blue.svg?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.38-green.svg?style=flat-square&logo=selenium)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.28-red.svg?style=flat-square&logo=cucumber)](https://cucumber.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.11-blue.svg?style=flat-square)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-green.svg?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-brightgreen.svg?style=flat-square)](https://github.com)

---

## 📖 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Modules Documentation](#modules-documentation)
- [Test Execution Guide](#test-execution-guide)
- [Configuration](#configuration)
- [Reporting & Results](#reporting--results)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Team](#team)
- [Support & Contact](#support--contact)

---

## Overview

**Apollo247 Automation Framework** is a robust, enterprise-grade end-to-end test automation solution for the Apollo247 healthcare platform. Built using industry best practices with **Selenium WebDriver** and **Cucumber (BDD)**, this framework automates comprehensive testing across 5 critical healthcare modules.

### 🎯 Purpose

- Automate healthcare workflows to ensure platform reliability
- Reduce manual testing time by automating repetitive tasks
- Validate user journeys from member selection to policy purchase
- Enable continuous integration with automated test execution
- Maintain consistent test coverage across modules

### 📊 Coverage

- **5 Modules** - Find Doctor, Health Insurance, Buy Medicine, Lab Tests, MyAccount
- **40+ Scenarios** - Positive, negative, and end-to-end flows
- **100+ Test Cases** - Comprehensive functional coverage
- **Multi-browser** - Chrome, Edge support

---

## Key Features

| Feature | Benefit | Details |
|---------|---------|---------|
| 🏗️ **Page Object Model (POM)** | Maintainable & Scalable | Encapsulates UI elements and interactions for easy updates |
| 📋 **BDD with Cucumber** | Business-Readable Tests | Non-technical stakeholders can understand test scenarios |
| 📊 **Data-Driven Testing** | Reusable Test Cases | Excel integration for testing multiple data sets |
| ⚡ **Explicit Waits** | Reliable Tests | WebDriverWait for dynamic elements without hard delays |
| 🔄 **Parallel Execution** | Faster Feedback | TestNG parallel runner reduces total execution time |
| 📸 **Screenshot Capture** | Quick Debugging | Automatic screenshots on test failure for investigation |
| 📈 **Extent Reports** | Rich Reporting | Advanced HTML reports with detailed test execution logs |
| 🌍 **Cross-Browser Testing** | Broad Compatibility | Execute tests across multiple browser environments |
| 🔐 **Headless Execution** | CI/CD Ready | Run tests without browser UI in pipelines |
| 🎯 **Framework Scalability** | Easy Extension | Add new modules and test cases without refactoring |

---

## Tech Stack

### Core Technologies

```
┌─────────────────────────────────────────┐
│        AUTOMATION FRAMEWORK             │
├─────────────────────────────────────────┤
│                                         │
│  • Java 11+           (Programming)     │
│  • Selenium 4.38   (Browser Automation) │
│  • Cucumber 7.28      (BDD Framework)   │
│  • TestNG 7.11        (Test Execution)  │
│  • Maven 3.6+         (Build Tool)      │
│                                         │
├─────────────────────────────────────────┤
│      SUPPORTING LIBRARIES               │
├─────────────────────────────────────────┤
│                                         │
│  • Apache POI 5.4.1   (Excel Handling)  │
│  • Extent Reports 5.1 (Advanced Reports)│
│  • Jackson 2.15.2     (JSON Processing) │
│                                         │
└─────────────────────────────────────────┘
```

### Dependency Versions

| Dependency | Version | Purpose |
|-----------|---------|---------|
| **Java** | 11+ | Primary programming language |
| **Selenium WebDriver** | 4.38.0 | Web browser automation |
| **Cucumber Core** | 7.28.0 | BDD framework |
| **Cucumber Java** | 7.28.0 | Java support for Cucumber |
| **Cucumber TestNG** | 7.28.0 | TestNG integration |
| **TestNG** | 7.11.0 | Test execution framework |
| **Apache POI** | 5.4.1 | Excel file operations |
| **Extent Reports** | 5.1.2 | Advanced HTML reporting |
| **Jackson Databind** | 2.15.2 | JSON processing |
| **PicoContainer** | 7.14.0 | Dependency injection |

---

## Quick Start

### Prerequisites Verification

```bash
# Check Java version (must be 11 or higher)
java -version
# Output: java version "11.0.x" or higher

# Check Maven version (must be 3.6 or higher)
mvn -version
# Output: Apache Maven 3.6.x or higher

# Verify Git installation
git --version
# Output: git version 2.x or higher
```

### Step 1️⃣ - Clone Repository

```bash
# Clone the Apollo247 automation framework
git clone https://github.com/apollo247/automation-framework.git

# Navigate to project directory
cd Testing-Sprint-Apollo247
```

### Step 2️⃣ - Install Dependencies

```bash
# Clean and install all dependencies
mvn clean install

# Skip tests during build (optional)
mvn clean install -DskipTests
```

### Step 3️⃣ - Configure Environment

```bash
# Open configuration file
# Edit: src/test/resources/config.properties

# Update the following properties:
app.url=https://staging.apollo247.com  # or production URL
browser=chrome                          # chrome, firefox, safari, edge
implicit.wait=10                        # seconds
explicit.wait=15                        # seconds
```

### Step 4️⃣ - Verify Installation

```bash
# Run a quick smoke test to verify setup
mvn clean test -Dtags="@smoke"

# If successful, you'll see test execution logs and report generation
```

---

## Project Structure

### Directory Organization

```
Testing-Sprint-Apollo247/
│
├── 📁 src/
│   │
│   ├── 📁 main/
│   │   ├── 📁 java/com/apollo247/testing/
│   │   │   │
│   │   │   ├── 📁 pages/                        # Page Objects (POM)
│   │   │   │   ├── 📁 FindDoctor/
│   │   │   │   │   ├── DoctorSearchPage.java
│   │   │   │   │   └── DoctorDetailPage.java
│   │   │   │   │
│   │   │   │   ├── 📁 HealthInsurance/
│   │   │   │   │   ├── MemberSelectionPage.java
│   │   │   │   │   ├── PlansListingPage.java
│   │   │   │   │   ├── PolicyDetailsPage.java
│   │   │   │   │   └── PolicyFormPage.java
│   │   │   │   │
│   │   │   │   ├── 📁 BuyMedicine/
│   │   │   │   ├── 📁 LabTest/
│   │   │   │   └── 📁 MyAccount/
│   │   │   │
│   │   │   └── 📁 utils/                        # Utilities
│   │   │       ├── WebDriverUtility.java
│   │   │       ├── BaseClass.java
│   │   │       ├── ExtendsReportsUtilities
|   |   |       ├── ExcelUtilities.java
|   |   |       ├── JavaScriptUtilities.java
|   |   |       ├── Pages.java
|   |   |       ├── ReaderUtilities.java
|   |   |       ├── SessionManager.java
|   |   |       ├── ActionUtilities.java
|   |   |       └── TakeScreenShotUtility.java
│   │   │
│   │   └── 📁 resources/
│   │       ├── file.png                
│   │       ├── fileupload.jpeg                  #uploading file               
│   │       └──📁 Reader/
│   │           ├── Apollo247_TestData.xlsx      # Logging setup
│   │           └── common.properties            # Environment configuration
│   │
│   └── 📁 test/
│       ├── 📁 java/com/apollo247/testing/
│       │   │
│       │   ├── 📁 stepdefinitions/              # Step Definitions
│       │   │   ├── FindDoctorSteps.java
│       │   │   ├── HealthInsuranceSteps.java
│       │   │   ├── BuyMedicineSteps.java
│       │   │   ├── LabTestSteps.java
│       │   │   └── MyAccountSteps.java
│       │   │
│       │   ├── 📁 hooks/                        # Cucumber Hooks
│       │   │   └── Hooks.java
│       │   │
│       │   └── 📁 runners/                      # TestNG Runners
│       │       ├── HealthInsurance_TestRunner.java
│       │       ├── RunnerIO_Docter.java
│       │       ├── BuyMedicineRunner.java
│       │       ├── LabTest_RunnerIO.java
│       │       └── MyAccountRunner.java
│       │
│       └── 📁 resources/
│           ├── 📁 Reports/                      # Reports
│           ├── 📁 Screenshots/                  # Screenshots img
│           ├── 📁 features/                     # Feature Files
│           │   ├──  FindDoctor.feature
│           │   ├──  HealthInsurance.feature
│           │   ├──  BuyMedicine.feature
│           │   ├──  LabTest.feature
│           │   └──  MyAccount.feature
│           │
│           └── 📁apollo247_session
│                ├──  cookies.data               # Session cookies
│                ├──  localStorage.json
│                └──  sessionStorage.json   
│
├── 📄 pom.xml                                   # Maven configuration
├── 📄 testng.xml                                # TestNG runner configuration
├── 📄 README.md                                 # This file
├── 📄 .gitignore                                # Git ignore rules
└── 📄 CONTRIBUTING.md                           # Contribution guidelines
```

### Directory Roles

| Directory | Purpose |
|-----------|---------|
| `pages/` | UI element locators and page interaction methods |
| `stepdefinitions/` | Maps Gherkin steps to Java code implementation |
| `features/` | Cucumber feature files with test scenarios in Gherkin |
| `runners/` | TestNG runner classes for test execution |
| `utils/` | Reusable utility functions and helpers |
| `testdata/` | Excel files containing test data for data-driven tests |

---

## Modules Documentation

### 🏥 1. Find Doctor Module

**Objective:** Automate doctor search, filtering, and appointment booking workflows.

#### Supported Scenarios

| Scenario | Type | Description |
|----------|------|-------------|
| Search by Speciality | Positive | Find doctors based on medical speciality |
| Filter by Price Range | Positive | Apply price filters to search results |
| Filter by Experience | Positive | Filter doctors by years of experience |
| Filter by Language | Positive | Filter doctors by consultation language |
| Book Appointment | Positive | Complete doctor appointment booking |
| Rebook Appointment | Positive | Reschedule existing doctor appointment |
| BMI Calculator | Positive | Calculate BMI using Excel test data |
| Missing Location | Negative | Validate error when location not selected |
| Invalid Speciality | Negative | Handle invalid medical speciality input |

#### Test Tags: `@doctor`, `@search `, `@booking`, `@bmi`

---

### 🏥 2. Health Insurance Module 

**Objective:** Automate comprehensive health insurance workflow from member selection to policy purchase.

#### Supported Scenarios

| Scenario | Type | Description |
|----------|------|-------------|
| Single Member Selection | Positive | Select self as insured member |
| Multiple Member Selection | Positive | Add multiple family members to policy |
| Coverage Filtering | Positive | Filter plans by coverage (₹10-24 Lakh) |
| Sort by Premium | Positive | Sort plans by ascending/descending cost |
| End-to-End Purchase | Positive | Complete policy purchase (13 steps) |
| No Member Selected | Negative | Error: "Select minimum one adult" |

#### Test Tags: `@memberSelection`, `@validation`, `@filtering`, `@policyPurchase`

---

### 💊 3. Buy Medicine Module

**Objective:** Automate medicine search, cart management, and ordering.

#### Supported Scenarios

| Scenario                | Type     | Description                                                  |
| ----------------------- | -------- | ------------------------------------------------------------ |
| Search Valid Medicine   | Positive | Search and display results for a valid medicine name         |
| Search Invalid Medicine | Negative | Validate behavior when searching for a non-existent medicine |
| Add to Cart             | Positive | Add selected medicine to the shopping cart successfully      |
| Update Cart Quantity    | Positive | Modify the quantity of items in the cart                     |
| Shop by Brand           | Positive | Browse and filter medicines based on brand selection         |
| Remove from Cart        | Positive | Remove a selected item from the shopping cart                |


#### Test Tags: `@pharmacy`, `@search`, `@cart`, `@brand`

---

### 🧪 4. Lab Test Module

**Objective:** Automate lab test search, prescription upload, and order management.

#### Supported Scenarios

| Scenario             | Type     | Description                                                            |
| -------------------- | -------- | ---------------------------------------------------------------------- |
| Search Valid Test    | Positive | Search and display results for a valid lab test                        |
| Search Invalid Test  | Negative | Handle search for an invalid or unavailable lab test                   |
| Upload Prescription  | Positive | Upload a valid prescription for booking lab tests                      |
| Invalid Prescription | Negative | Validate error handling for unsupported or invalid prescription upload |
| Filter by Price      | Positive | Apply price filters to refine lab test search results                  |
| Radiology Workflow   | Positive | Complete booking flow for radiology-related tests                      |


#### Test Tags: `@labTest`, `@search`, `@prescription`, `@radiology`

---

### 👤 5. MyAccount Management Module

**Objective:** Automate account settings, family member management, and user preferences.

#### Supported Scenarios

| Scenario              | Type     | Description                                           |
| --------------------- | -------- | ----------------------------------------------------- |
| Manage Family Members | Positive | Add, edit, or remove family member details in profile |
| View Appointments     | Positive | View list of upcoming and past appointments           |
| Verify Memberships    | Positive | Check and validate active user memberships            |
| Notification Settings | Positive | Update and manage notification preferences            |
| Help Section          | Positive | Access help resources and support options             |
| Logout                | Positive | Successfully log out from the application             |


#### Test Tags: `@account`, `@family`, `@appointments`, `@membership`, `@logout`

---

## Test Execution Guide

### Basic Execution Commands

```bash
# Execute all tests
mvn clean test

# Execute all tests with detailed output
mvn clean test -X
```

### Module-Specific Execution

```bash
# 🏥 Find Doctor Module
mvn test -Dtags="@doctor"

# 🏥 Health Insurance Module
mvn test -Dtags="@insurance"

# 💊 Buy Medicine Module
mvn test -Dtags="@pharmacy"

# 🧪 Lab Tests Module
mvn test -Dtags="@labTest"

# 👤 MyAccount Management Module
mvn test -Dtags="@account"
```

### Test Type Execution

```bash
# 🚀 Smoke Tests
mvn test -Dtags="@smoke"

# 🔄 Regression Tests
mvn test -Dtags="@regression"

# 🔁 End-to-End Tests
mvn test -Dtags="@endToEnd"
```

### Advanced Execution Options

```bash
# Combine tags (AND logic)
mvn test -Dtags="@insurance and @policyPurchase"

# Exclude tags (NOT logic)
mvn test -Dtags="not @negative"

# Parallel execution
mvn test -DthreadCount=4

# Specific feature file
mvn test -Dcucumber.options="src/test/resources/features/HealthInsurance/PolicyPurchase.feature"

# Headless mode
mvn test -Dheadless=true
```

---

## Configuration

### Application Configuration (config.properties)

```properties
# 📍 Application
app.url=https://staging.apollo247.com
login.username=testuser@apollo247.com
login.password=TestPassword@123

# 🌐 Browser
browser=chrome
headless.mode=false
maximize.window=true

# ⏱️ Waits (seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=30

# 📊 Reporting
screenshot.on.failure=true
screenshot.path=screenshots/
report.path=reports/

# 📁 Test Data
testdata.path=src/test/resources/testdata/
```

---

## Reporting & Results

### Report Locations

After execution, reports are generated at:

```
📁Reports/
├── Apollo247-reports/                    # TestNG Report
│   └── index.html
├── Apollo247-cucumber-html-reports/      # Cucumber Report
│   └── index.html
├──📁 screenshots/                        # Failure Screenshots
└── logs/                                  # Execution Logs
```

### View Reports

```bash
# Open TestNG Report
open Report/Apollo247-reports/index.html

# Open Cucumber Report
open target/Apollo247-cucumber-html-reports/index.html
```

---

## Best Practices

### ✅ DO

```java
// Use Page Object Model
public class PolicyDetailsPage {
    private By acceptButton = By.id("acceptTerms");
    
    public void acceptTerms() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(acceptButton));
        btn.click();
    }
}

// Use explicit waits
new WebDriverWait(driver, Duration.ofSeconds(15))
    .until(ExpectedConditions.elementToBeClickable(locator));

// Externalize test data
String data = ExcelDataProvider.getValue("Sheet1", "A1");

// Use meaningful test names
@Test(description = "Verify user can book doctor appointment")
```

### ❌ DON'T

```java
// Don't scatter locators in tests
driver.findElement(By.id("button")).click();

// Don't use hard delays
Thread.sleep(5000);

// Don't hard-code test data
String memberName = "John Doe";

// Don't have silent failures
try { element.click(); } catch(Exception e) { }
```

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| **Element not found** | Check XPath in DevTools, increase wait time |
| **Test timeout** | Increase `explicit.wait` in config |
| **Stale element** | Re-find element before interaction |
| **Element not clickable** | Scroll to element, check visibility |
| **Screenshot error** | Create `target/screenshots/` directory |

---

## Team

| 👤 Name | 📦 Module | 📧 Email |  
|---------|----------|---------|
| **Gokul Anand S** | Health Insurance |sganand05@gmail.com  |
| **Shyam Sundar K** | Lab Test | shyamson366@gmail.com |
| **Roshini Ravikumar** | Buy Medicine |roshijay2004@gmail.com |
| **Priyanka G** | MyAccount Management | priyankagajendran17@gmail.com|
| **Swetha B** | Find Doctor |swethababupec@gmail.com|

---

## Support & Contact

### 📞 Support Channels

- **Slack:** #automation-testing
- **Email:** automation@apollo247.com
- **Jira:** APOLLO-QA project
- **Wiki:** [Internal Documentation](https://wiki.apollo247.com)

### 🐛 Reporting Issues

Create a Jira issue with:
1. Clear title and description
2. Steps to reproduce
3. Expected vs actual result
4. Screenshots/logs
5. Environment details (browser, OS, environment)

---

## License

Proprietary to Apollo247 Health Services. Unauthorized reproduction prohibited.

---

<div align="center">

### 🎉 Thank You for Using Apollo247 Automation Framework!

**Questions?** Slack us at #automation-testing  
**Found a bug?** Create an issue on Jira  
**Have suggestions?** We'd love to hear from you!

---

**Version:** 1.0.0 | **Last Updated:** April 2025 | **Status:** ✅ Production Ready

</div>
