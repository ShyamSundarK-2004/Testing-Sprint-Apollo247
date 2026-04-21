package com.apollo247.testing.stepdefinitions;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import com.apollo247.testing.utilities.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LabTestSteps {

	BaseClass b;

	public LabTestSteps(BaseClass b) {
		this.b = b;
	}

	// ================= DashBoard Scenarios =================

	@Given("User is on Lab Tests page")
	public void user_is_on_lab_tests_page() {
		b.getPages().dashboardPage.clickOnModule("Lab Tests");
//		b.getDriver().findElement(By.cssSelector("[href='https://apollo247insurance.com/health-insurance']")).click();
		b.getPages().labTestPage.closePopupIfPresent();
	}

	@Then("check user in on correct module")
	public void check_user_in_on_correct_module() {
		String title = b.getPages().labTestPage.getCurrentPageUrl();
		assertTrue(title.contains("lab-tests"), "Page not Navigated to the module");
	}

	// ================= SEARCH Scenarios =================

	@When("User searches for {string}")
	public void user_searches_for(String testName) {
		b.getPages().labTestPage.closePopupIfPresent();
		b.getPages().labTestPage.searchTest(testName);

	}

	@Then("validate search result for {string}")
	public void validate_search_result_for(String type) {

		switch (type.toLowerCase()) {

		case "valid":
			assertTrue(b.getPages().labTestPage.isResultDisplayed(), "Cards are not displayed for valid search");
			break;

		case "invalid":
			assertTrue(b.getPages().labTestPage.isErrorMessageDisplayed(),
					"Error message not displayed for invalid input");
			break;

		default:
			throw new IllegalArgumentException("Invalid type: " + type);
		}
	}

	// ================= PRESCRIPTION FLOW WITH VALID FILE =================

	@When("User clicks on book test using prescription")
	public void user_clicks_on_book_test_using_prescription() {
		b.getPages().labTestPage.closePopupIfPresent();

		b.getPages().labTestPage.clickOnBookByPrescriptionModule();
	}

	@When("User uploads valid prescription")
	public void user_uploads_valid_prescription() {

		String path = "C:\\Users\\Shyam Sundar\\Documents\\prescription2.jpeg";

		b.getPages().bookByPrescriptionPage.uploadFile(path);

		assertTrue(b.getPages().bookByPrescriptionPage.isFileAttached(), "Prescription file not uploaded successfully");

	}

	@Then("verify  proceed button is enabled")
	public void verify_proceed_button_is_enabled() {
		assertTrue(b.getPages().bookByPrescriptionPage.isProceedBtnEnabled(), "Proceed button is not enabled");
	}

	// ================= PRESCRIPTION FLOW WITH VALID INVALID FILE =================

	@Then("verify invalid file message displayed and click on ok")
	public void verify_invalid_file_message_displayed_and_click_on_ok() {

		String path = "C:\\Users\\Shyam Sundar\\Documents\\prescription.webp";

		b.getPages().bookByPrescriptionPage.uploadFile(path);

		assertTrue(b.getPages().bookByPrescriptionPage.isWrongFileUploaded(), "Wrong file type is accepted");

		b.getPages().bookByPrescriptionPage.closeInvalidMessagePopup();

	}

	@Then("verify proceed button is not enabled")
	public void verify_proceed_button_is_not_enabled() {
		assertTrue(b.getPages().bookByPrescriptionPage.isProceedBtnEnabled(), "Proceed button is enabled");
	}

	// ================= Radiology Scenarios =================

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
		assertTrue(url.contains("radiology"), "Not Switched to Radiology page");
	}

	@When("User enters radiology details")
	public void user_enters_radiology_details(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> allData = dataTable.asMaps();

		for (Map<String, String> row : allData) {

			String city = row.get("city");
			String hospital = row.get("hospital");
			String testName = row.get("tests");
			String date = row.get("date");
			String filePath = row.get("filePath");

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

	// ================= My Order Checking Scenarios =================

	@Given("User should be on orders page")
	public void user_should_be_on_orders_page() {
		b.getPages().labTestPage.closePopupIfPresent();

		b.getPages().labTestPage.clickOnViewReportInMyOrder();

		String url = b.getPages().myOrderPage.getCurrentUrl("orders");
		assertTrue(url.contains("orders"), "Not in MyorderPage");

	}

	@When("User clicks on patient dropdown")
	public void user_clicks_on_patient_dropdown() {
		b.getPages().myOrderPage.clickOnUserDropdown();
	}

	@Then("User select a name {string} and check orders")
	public void user_select_a_name_and_check_orders(String userName) {
		String user = b.getPages().myOrderPage.clickOnSpecificUser(userName);
		boolean flag = b.getPages().myOrderPage.isSpecificUserOrderDisplayed(user);
		assertTrue(flag, "Different user orders found");
	}
}