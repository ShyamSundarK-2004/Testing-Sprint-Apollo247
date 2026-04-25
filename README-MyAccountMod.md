Account Module Automation - Apollo247
Overview

This branch focuses on automating the Account Module of Apollo247 using Selenium with BDD (Cucumber).

It covers multiple user flows including family management, appointments, memberships, notifications, help section, and logout functionality.

Scope of This Branch
Account Module only
Covers both positive and negative scenarios
Includes UI validations and workflow testing
Tech Stack
Java
Selenium WebDriver
Cucumber (BDD)
TestNG
Maven
Test Scenarios Implemented
1. Manage Family Members

Positive Case

Add family members using Excel data
Validate successful profile creation

Negative Case

Attempt to add member with empty details
Validate error message display
2. My Appointments
Verify My Appointments page loads successfully
Validate data persistence after page refresh
3. My Memberships

Negative Case

Enter invalid corporate email
Validate error message and popup handling

Positive Case

Select membership plan
Validate plan details (duration & price)
4. Notification Preferences
Navigate to Notification Preferences page

Scenarios Covered

Enable Push Notifications
Enable SMS Notifications
Enable multiple notification types
Validate toggles are active
5. Need Help Section
Verify all help categories are displayed
Navigate to Medicines help section
Validate page load
6. Logout Functionality
Click profile icon and logout
Validate redirection to login page
Key Features
BDD Scenario & Scenario Outline implementation
Data-driven testing (Excel integration)
Positive & Negative test coverage
Reusable step definitions
Page Object Model (POM) design
Feature File Highlights
Background
User opens My Account panel
Ensures user is logged into the application before execution
Tags Used
@ManageFamily
@MyAppointments
@MyMemberships
@Notifications
@NeedHelp
@Logout
@Negative
How to Execute
Clone the repository
Navigate to this branch
Run the TestRunner class
Future Enhancements
Selenium Grid integration
CI/CD pipeline (Jenkins)
Parallel execution
Advanced reporting (Extent Reports / Allure)

Author
Priyanka G
