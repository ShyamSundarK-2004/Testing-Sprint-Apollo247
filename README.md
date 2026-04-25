# 🧪 Lab Test Module Automation (Branch)

## 📌 Overview
This branch contains automation scripts specifically developed for the **Lab Test module** of Apollo247 using Selenium with BDD (Cucumber).

The implementation focuses on validating critical Lab Test workflows including search functionality, prescription upload, radiology requests, order filtering, and end-to-end booking.

---

## 🎯 Scope
- Covers only Lab Test module  
- Includes both positive and negative scenarios  
- Uses data-driven and BDD approach  
- Simulates real user workflows  

---

## 🧰 Tech Stack
- Java  
- Selenium WebDriver  
- Cucumber (BDD)  
- TestNG  
- Maven  

---

## 🏗️ Implementation Design
- Page Object Model (POM) for UI abstraction  
- Step Definitions mapped to feature steps  
- Background for common navigation setup  
- Scenario Outline for data-driven testing  
- Hooks for setup and teardown  

---

## 🧪 Scenarios Covered

### 🔍 Search Functionality
- Validates lab test search using multiple inputs  
- Handles both valid and invalid data  
- Ensures accurate result behavior  

---

### 📄 Prescription Upload

#### ✅ Positive Case
- Upload valid prescription  
- Verify proceed button is enabled  

#### ❌ Negative Case
- Upload invalid prescription format  
- Verify error popup is displayed  

---

### 🏥 Radiology Flow
- Navigate to radiology section  
- Enter required details (city, hospital, date, test, file)  
- Validate request call button  

---

### 📦 Order Filtering
- Select patient from dropdown  
- Validate filtered orders based on selection  

---

### 🔁 End-to-End Booking Flow
- Search and select lab test  
- Add test to cart  
- Enter patient details (data-driven)  
- Select slot and address  
- Proceed to payment  
- Validate payment page  

---

## 📁 Feature Design Highlights

### Background
- Navigates user to Lab Test page before each scenario  
- Ensures correct module is loaded  

---

### Tags Used
- @labTest  
- @searchScenario  
- @prescriptionScenario  
- @radiologyScenario  
- @myOrder  
- @EndToEndScenario  
- @smoke  
- @sanity  
- @regression  

---

## 🧪 Execution
- Executed using TestNG Cucumber Runner  
- Supports tag-based execution  
- Extendable for parallel execution  

---

## ⚙️ How to Run
1. Clone repository  
2. Switch to this branch  
3. Open in IDE (Eclipse/IntelliJ)  
4. Run TestRunner class  

---

## 💡 Note
This branch focuses only on Lab Test module automation and is part of a scalable automation framework.

---

## 👨‍💻 Author
Shyam Sundar K
