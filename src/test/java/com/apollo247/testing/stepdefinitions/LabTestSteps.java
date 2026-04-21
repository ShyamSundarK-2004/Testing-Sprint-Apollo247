package com.apollo247.testing.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import com.apollo247.testing.utilities.BaseClass;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LabTestSteps {

	BaseClass b;

	// Constructor Injection → gets BaseClass instance
	public LabTestSteps(BaseClass b) {
		this.b = b;
	}

	// ================= Background Scenarios =================

	@Given("User is on Lab Tests page")
	public void user_is_on_lab_tests_page() {

		// Click on Lab Tests module
		b.getPages().dashboardPage.clickOnModule("Lab Tests");

		// Close popup if present
		b.getPages().labTestPage.closePopupIfPresent();
	}

	@Then("check user in on correct module")
	public void check_user_in_on_correct_module() {

		// Get current page URL
		String title = b.getPages().labTestPage.getCurrentPageUrl();

		// Validate correct navigation
		assertTrue(title.contains("lab-tests"), "Page not Navigated to the module");
	}

	// ================= SEARCH Scenarios =================

	@When("User searches for {string}")
	public void user_searches_for(String testName) {

		// Close popup if exists
		b.getPages().labTestPage.closePopupIfPresent();

		// Perform search
		b.getPages().labTestPage.searchTest(testName);
	}

	@Then("validate search result for {string}")
	public void validate_search_result_for(String type) {

		// Switch based on type (valid / invalid)
		switch (type.toLowerCase()) {

		case "valid":
			// Validate results are displayed
			assertTrue(b.getPages().labTestPage.isResultDisplayed(), "Cards are not displayed for valid search");
			break;

		case "invalid":
			// Validate error message is shown
			assertTrue(b.getPages().labTestPage.isErrorMessageDisplayed(),
					"Error message not displayed for invalid input");
			break;

		default:
			// Handle unexpected input
			throw new IllegalArgumentException("Invalid type: " + type);
		}
	}

	// ================= PRESCRIPTION FLOW =================

	@When("User clicks on book test using prescription")
	public void user_clicks_on_book_test_using_prescription() {

		// Close popup
		b.getPages().labTestPage.closePopupIfPresent();

		// Click prescription module
		b.getPages().labTestPage.clickOnBookByPrescriptionModule();
	}

	@When("User uploads valid prescription")
	public void user_uploads_valid_prescription() {

		// File path for upload
		String path = "C:\\Users\\Shyam Sundar\\Documents\\prescription2.jpeg";

		// Upload file
		b.getPages().bookByPrescriptionPage.uploadFile(path);

		// Validate file uploaded
		assertTrue(b.getPages().bookByPrescriptionPage.isFileAttached(), "Prescription file not uploaded successfully");
	}

	@Then("verify  proceed button is enabled")
	public void verify_proceed_button_is_enabled() {

		// Validate proceed button
		assertTrue(b.getPages().bookByPrescriptionPage.isProceedBtnEnabled(), "Proceed button is not enabled");
	}

	// ================= INVALID FILE SCENARIO =================

	@Then("verify invalid file message displayed and click on ok")
	public void verify_invalid_file_message_displayed_and_click_on_ok() {

		String path = "C:\\Users\\Shyam Sundar\\Documents\\prescription.webp";

		// Upload invalid file
		b.getPages().bookByPrescriptionPage.uploadFile(path);

		// Validate error shown
		assertTrue(b.getPages().bookByPrescriptionPage.isWrongFileUploaded(), "Wrong file type is accepted");

		// Close popup
		b.getPages().bookByPrescriptionPage.closeInvalidMessagePopup();
	}

	@Then("verify proceed button is not enabled")
	public void verify_proceed_button_is_not_enabled() {

		// Validate button state
		assertTrue(b.getPages().bookByPrescriptionPage.isProceedBtnEnabled(), "Proceed button is enabled");
	}

	// ================= RADIOLOGY =================

	@When("User clicks on lab test search bar")
	public void user_clicks_on_lab_test_search_bar() {

		b.getPages().labTestPage.closePopupIfPresent();
		b.getPages().labTestPage.clickOnSearchBox();
	}

	@When("User clicks on explore radiology option and switch to radiology tab")
	public void user_clicks_on_explore_radiology_option_and_switch_to_radiology_tab() {

		b.getPages().labTestPage.clickOnRadiologyBookingBtn();
	}

	@Then("User should be on radiology page")
	public void user_should_be_on_radiology_page() {

		String url = b.getPages().radiologyPage.getCurrentPageUrl();

		// Validate navigation
		assertTrue(url.contains("radiology"), "Not Switched to Radiology page");
	}

	@When("User enters radiology details")
	public void user_enters_radiology_details(DataTable dataTable) {

		List<Map<String, String>> allData = dataTable.asMaps();

		// Loop through each row
		for (Map<String, String> row : allData) {

			String city = row.get("city");
			String hospital = row.get("hospital");
			String testName = row.get("tests");
			String date = row.get("date");
			String filePath = row.get("filePath");

			// Fill details
			b.getPages().radiologyPage.chooseCity(city);
			b.getPages().radiologyPage.chooseHospital(hospital);
			b.getPages().radiologyPage.chooseDate(date);
			b.getPages().radiologyPage.chooseTestName(testName);
			b.getPages().radiologyPage.UploadPrescription(filePath);
		}
	}

	@Then("User should see request call button is enabled")
	public void user_should_see_request_call_button_is_enabled() {

		assertTrue(b.getPages().radiologyPage.isRequestCallBtnEnabled(), "Request Call Button is not Enabled");
	}

	// ================= MY ORDERS =================

	@Given("User should be on orders page")
	public void user_should_be_on_orders_page() {

		b.getPages().labTestPage.closePopupIfPresent();
		b.getPages().labTestPage.clickOnViewReportInMyOrder();

		String url = b.getPages().myOrderPage.getCurrentUrl("orders");

		// Validate page
		assertTrue(url.contains("orders"), "Not in MyorderPage");
	}

	@When("User clicks on patient dropdown")
	public void user_clicks_on_patient_dropdown() {

		b.getPages().myOrderPage.clickOnUserDropdown();
	}

	@Then("User select a name {string} and check orders")
	public void user_select_a_name_and_check_orders(String userName) {

		String user = b.getPages().myOrderPage.clickOnSpecificUser(userName);

		// Validate orders
		boolean flag = b.getPages().myOrderPage.isSpecificUserOrderDisplayed(user);

		assertTrue(flag, "Different user orders found");
	}
}