Apollo247 End-to-End Functional Testing
Overview
This project focuses on automating end-to-end functional testing of the Apollo247 platform using Selenium with BDD (Cucumber).
It covers multiple real-time user workflows including doctor booking, filtering, rebooking, BMI calculation, and validation scenarios.
Scope of This Module
•	End-to-End user journey automation
•	Covers both positive and negative scenarios
•	Includes UI validation and workflow testing
•	Data-driven testing using Excel
Tech Stack
•	Java
•	Selenium WebDriver
•	Cucumber (BDD)
•	TestNG
•	Maven
Test Scenarios Implemented
1. Doctor Booking
•	Search doctor by speciality (Andrology)
•	Select available doctor
•	Schedule hospital visit appointment
•	Validate phone number and consultation amount
 2. Filter Doctor and Book
•	Select General Physician
•	Apply sorting (Price: Low to High)
•	Filter by experience (6–10 years)
•	Filter by language (English)
•	Open doctor profile
•	Submit and verify review
3. Rebook Appointment
•	Navigate to My Appointments
•	 Click Rebook option
•	Change patient details
•	Validate "Book Appointment" option is displayed
 4. BMI Calculator (Data-Driven Testing)
•	Navigate to Health Tools
•	Select BMI Calculator
•	Enter data from Excel
•	Validate BMI category result
5. Doctor Description Validation
•	Select doctor using Scenario Outline
•	Validate doctor experience and language details
•	Sample Data Covered:
•	Dr. S K Agarwal
•	Dr. Pawan Sharma
 6. Negative Test Scenarios 
•	Missing Location Validation
•	Attempt booking without selecting location
•	Validate pincode error message
•	Invalid Speciality
•	Search with invalid speciality
•	Validate "No doctors found" message
Key Features
•	BDD with Scenario & Scenario Outline
•	Data-driven testing (Excel integration)
•	Positive & Negative coverage
•	Reusable step definitions
•	Page Object Model (POM) design
•	feature File Highlights
Background
•	User navigates to Find Doctor module
Tags Used
•	@doctorBooking
•	@filter
•	@rebook
•	@bmi
•	@description
•	@Negative
•	@Negative1
How to Execute
•	Clone the repository
•	Navigate to the project folder
•	Run the TestRunner class
•	Execute using TestNG
•	Future Enhancements
•	Parallel execution
•	Selenium Grid integration
•	CI/CD (Jenkins)
•	Advanced reporting (Extent Reports / Allure)
Author
Swetha B

