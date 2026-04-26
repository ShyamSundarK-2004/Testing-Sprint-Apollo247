package com.apollo247.testing.stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.ExcelUtilities;
import com.apollo247.testing.utilities.ExtendsReportsUtilities;
import com.apollo247.testing.utilities.SessionManager;
import com.apollo247.testing.utilities.TakeScreenShotUtility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HealthInsurance_stepdefinition {
	// your existing browser/page object ref
	BaseClass b;
	ExcelUtilities excelUtility = new ExcelUtilities();

	// Sheet name constant
	private static final String SHEET = "Policy_purchase_Data";

	// Row to read — rowNum 3 = first data row (0-indexed:
	// 0=banner,1=group,2=header,3=data)
	int row = 4; // change to 4,5,6,7 for TC_INS_002..005

	public HealthInsurance_stepdefinition(BaseClass b) {
		this.b = b;
	}

	@Given("User navigates to Health Insurance page and enter pincode {string}")
	public void user_navigates_to_health_insurance_page_and_enter_pincode(String pincode) {
		b.getPages().dashboardPage.clickonHealthInsuranceModule();
		// for using cookies in insurance module
		try {
			SessionManager.switchToDomain(b.getDriver(), "https://www.apollo247insurance.com/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		b.getPages().healthInsurancePage.clickCancelSelectLocation();
		b.getPages().dashboardPage.clickOnModule("Health Insurance");
		b.getPages().healthInsurancePage.performEnterPinCode(pincode);

		System.out.println("Enter pincode");

	}

	@When("User selects {string} and {string} at the age {string} as members")
	public void user_selects_and_at_the_age_as_members(String gender, String m1, String m1Age) {

		b.getPages().healthInsurancePage.selectGender(gender);
		b.getPages().healthInsurancePage.unselectMember();
		b.getPages().healthInsurancePage.selectMember(m1, m1Age);
		System.out.println("User gender  and memeber selected");

	}

	@When("User clicks on {string}")
	public void user_clicks_on(String viewPlans) {
		b.getPages().healthInsurancePage.clickViewButton(viewPlans);
		System.out.println("view plans is clicked");

	}

	@Then("Insurance plans should be loaded successfully")
	public void insurance_plans_should_be_loaded_successfully() {

		Assert.assertTrue(b.getPages().healthInsuranceProductListings.viewPlanHeader().isDisplayed(),
				"View Plans header not displayed");
		Assert.assertTrue(b.getPages().healthInsuranceProductListings.viewNumberOfPlans().size() > 0,
				"No plans found on page");
		System.out.println("Plans are shown");

	}

	@When("User clicks on {string} without selecting any member")
	public void user_clicks_on_without_selecting_any_member(String viewPlans) {
		b.getPages().healthInsurancePage.unselectMember();
		b.getPages().healthInsurancePage.clickViewButton(viewPlans);
		System.out.println("Clicked view Plans");
	}

	@Then("Proper validation error message {string} should be displayed")
	public void proper_validation_error_message_should_be_displayed(String expectedMessage) {
		String actualMessage = b.getPages().healthInsurancePage.errorMessageNoMemeberSelected();
		Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch");
		System.out.println("Validated successfully");
	}

	@When("User selects members with following details")
	public void user_selects_members_with_following_details(io.cucumber.datatable.DataTable dataTable) {

		List<Map<String, String>> members = dataTable.asMaps(String.class, String.class);
		// Set gender once
		String gender = members.get(0).get("Gender");
		b.getPages().healthInsurancePage.selectGender(gender);
		// Ensure unselect happens ONLY once
		boolean isCleared = false;

		for (Map<String, String> member : members) {

			if (!isCleared) {
				b.getPages().healthInsurancePage.unselectMember();
				isCleared = true;
			}

			String memberName = member.get("Member");
			String age = member.get("Age");

			b.getPages().healthInsurancePage.selectMember(memberName, age);
		}
	}

	// Implemented user clicks on "View Plans" in another stepdefinition file
	// so clicked "View plans" button

	@Then("Family insurance plans should be displayed correctly")
	public void family_insurance_plans_should_be_displayed_correctly() {
		Assert.assertTrue(b.getPages().healthInsuranceProductListings.isNoPlansMessageDisplayed(),
				"'No plans found' message not displayed correctly");
		Assert.assertTrue(b.getPages().healthInsuranceProductListings.isPlansAvailable(),
				"Plans are not displayed properly");
		System.out.println("Validated");
	}

	// @filtering
	// User selected Male self at age 22

	@When("User applies coverage filter between {string}")
	public void user_applies_coverage_filter_between(String coverageAmount) {
		b.getPages().healthInsuranceProductListings.coverageAmount(coverageAmount);
		System.out.println("coverage amt selected");

	}

	@When("User selects room rent type as {string}")
	public void user_selects_room_rent_type_as(String roomRentType) {
		b.getPages().healthInsuranceProductListings.roomRentType(roomRentType);
		System.out.println("roomrent type selected");

	}

	@When("User sorts plans by {string}")
	public void user_sorts_plans_by(String plan) {
		b.getPages().healthInsuranceProductListings.sortByPlans(plan);
		System.out.println("sort plans selected");

	}

	@Then("Plans should be displayed based on applied filters coverage amount {string} and {string}")
	public void plans_should_be_displayed_based_on_applied_filters_coverage_amount_and(String coverageAmt,
			String planType) {

		boolean isValid = b.getPages().healthInsuranceProductListings.isMatch(coverageAmt);
		Assert.assertTrue(isValid, "Coverage filter validation failed");
		Assert.assertTrue(b.getPages().healthInsuranceProductListings.isTextMatching(planType), "Text does not match!");
		System.out.println("amt and plans validated");

	}

// Insurance Policy form validate inputs
	@When("User clicks proceeds with plan customization")
	public void user_clicks_proceeds_with_plan_customization() {
		b.getPages().healthInsuranceProductListings.clickProceedsPlan();
		b.getPages().healthInsurance_PolicyReview.clickPolicyProceedButton();
	}

	@When("User completes step1 Member Details form")
	public void user_completes_step1_member_details_form() {
		String fn = excelUtility.getExcelData(SHEET, row, 6); // FirstName
		String ln = excelUtility.getExcelData(SHEET, row, 7); // LastName
		String dob = excelUtility.getExcelData(SHEET, row, 8); // DateOfBirth
		String ft = excelUtility.getExcelData(SHEET, row, 9); // Height_ft
		String in_ = excelUtility.getExcelData(SHEET, row, 10); // Height_in
		String wt = excelUtility.getExcelData(SHEET, row, 11); // Weight_kg

		b.getPages().healthInsurance_InsuranceForm.fillMemberDetails(fn, ln, dob, ft, in_, wt);
	}

	@When("User completes  step2 Medical Questions form")
	public void user_completes_step2_medical_questions_form() {
		String q1 = excelUtility.getExcelData(SHEET, row, 12); // MedQ1
		String q2 = excelUtility.getExcelData(SHEET, row, 13); // MedQ2
		String q3 = excelUtility.getExcelData(SHEET, row, 14); // MedQ3
		String q4 = excelUtility.getExcelData(SHEET, row, 15); // MedQ4
		String q5 = excelUtility.getExcelData(SHEET, row, 16); // MedQ5

		b.getPages().healthInsurance_InsuranceForm.fillMedicalQuestions(q1, q2, q3, q4, q5);
	}

	@When("User completes step3 Proposer Details form")
	public void user_completes_step3_proposer_details_form() {
		String email = excelUtility.getExcelData(SHEET, row, 17); // Email
		String mob = excelUtility.getExcelData(SHEET, row, 18); // Mobile
		String altMob = excelUtility.getExcelData(SHEET, row, 19); // AltMobile
		String proFN = excelUtility.getExcelData(SHEET, row, 20); // Salutation
		String pan = excelUtility.getExcelData(SHEET, row, 21); // PAN

		b.getPages().healthInsurance_InsuranceForm.fillProposerDetailForm(email, mob, altMob, proFN, pan);
	}

	@When("User completes step4 KYC Verification  upload document")
	public void user_completes_step4_kyc_verification_upload_document() {
		String kycType = excelUtility.getExcelData(SHEET, row, 22); // KYC_DocType
		String kycNum = excelUtility.getExcelData(SHEET, row, 23); // KYC_DocNumber

		b.getPages().healthInsurance_InsuranceForm.fillKYCVerificationForm(kycType, kycNum);
	}

	@When("User completes step5 address proof  upload document")
	public void user_completes_step5_address_proof_upload_document() throws InterruptedException {
		String addrType = excelUtility.getExcelData(SHEET, row, 25); // AddrProof_DocType
		String addrNum = excelUtility.getExcelData(SHEET, row, 26); // AddrProof_DocNumber

		b.getPages().healthInsurance_InsuranceForm.fillAddressProofForm(addrType, addrNum);
	}

	@When("User completes step6 Address Details")
	public void user_completes_step6_address_details() throws InterruptedException, IOException {
		String street = excelUtility.getExcelData(SHEET, row, 28); // StreetAddress
		String city = excelUtility.getExcelData(SHEET, row, 29); // City
		String pin = excelUtility.getExcelData(SHEET, row, 30); // Pincode

		b.getPages().healthInsurance_InsuranceForm.fillAddressDetails(street, city, pin);

		// Log step in report
		ExtendsReportsUtilities.logStep("When", "User fills Address Details");

		// 📸 Take screenshot intentionally (even if step passes)
		String path = new TakeScreenShotUtility().takeScreenShot(b.getDriver(), "AddressDetails_Filled");

		// Attach screenshot to report
		ExtendsReportsUtilities.fail("Address details entered (including empty validation case)");
		ExtendsReportsUtilities.attachScreenshot(path);
	}

	@When("User completes step7 Nomiee Selection")
	public void user_completes_step7_nomiee_selection() {
		String nFN = excelUtility.getExcelData(SHEET, row, 31); // Nom_FirstName
		String nLN = excelUtility.getExcelData(SHEET, row, 32); // Nom_LastName
		String nRel = excelUtility.getExcelData(SHEET, row, 33); // Nom_Relation
		String nDOB = excelUtility.getExcelData(SHEET, row, 34); // Nom_DOB
		String aFN = excelUtility.getExcelData(SHEET, row, 35); // App_FirstName
		String aLN = excelUtility.getExcelData(SHEET, row, 36); // App_LastName
		String aDOB = excelUtility.getExcelData(SHEET, row, 37); // App_DOB
		String aRel = excelUtility.getExcelData(SHEET, row, 38); // App_Relation

		b.getPages().healthInsurance_InsuranceForm.fillNomineeSelection(nFN, nLN, nRel, nDOB, aFN, aLN, aDOB, aRel);
	}

	@When("User completes step8 Bank Account Details")
	public void user_completes_step8_bank_account_details() {
		String accNum = excelUtility.getExcelData(SHEET, row, 39); // BankAccNumber
		String accTyp = excelUtility.getExcelData(SHEET, row, 40); // BankAccType
		String ifsc = excelUtility.getExcelData(SHEET, row, 41); // IFSC_Code

		b.getPages().healthInsurance_InsuranceForm.fillBankAccountDetails(accNum, accTyp, ifsc);
	}

	@When("User completes all the forms and clicks")
	public void user_completes_all_the_forms_and_clicks() {
		b.getPages().healthInsurance_InsuranceForm.clickNextBtn();
		b.getPages().healthInsurance_InsuranceForm.clickNextBtn();

	}

	@When("User accepts Terms and Conditions")
	public void user_accepts_terms_and_conditions() {
		b.getPages().healthInsurance_InsuranceForm.acceptsTC();
	}

	@Then("User reviews policy details")
	public void user_reviews_policy_details() {
		String name = excelUtility.getExcelData(SHEET, row, 42); // PolicyHolderName
		SoftAssert softAssert = new SoftAssert();
		boolean isValid = b.getPages().healthInsurance_InsuranceForm.reviewPolicyDetails(name);
		softAssert.assertTrue(isValid, "Policy details review failed for member: " + name);
		softAssert.assertAll();
	}

	@When("User clicks on policy form")
	public void user_clicks_on_policy_form() {
		String btn = excelUtility.getExcelData(SHEET, row, 43); // BuyNow
		b.getPages().healthInsurance_InsuranceForm.policyBUY_NOW(btn);
	}

	@Then("The Payment Options page should be displayed with text and validating the policy")
	public void the_payment_options_page_should_be_displayed_with_text_and_validating_the_policy() throws IOException {

		String pay = excelUtility.getExcelData(SHEET, row, 44); // PayVerify
		String payOpt = excelUtility.getExcelData(SHEET, row, 45); // PaymentOptions
		String toPay = excelUtility.getExcelData(SHEET, row, 46); // ToPay

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(b.getPages().healthInsurance_InsuranceForm.verifyPaymentPage(pay),
				"❌ Payment page NOT loaded");
		softAssert.assertTrue(b.getPages().healthInsurance_InsuranceForm.verifyPaymentOption(payOpt),
				"❌ 'Payment Options' text NOT visible");
		softAssert.assertTrue(b.getPages().healthInsurance_InsuranceForm.verifyAmtVisible(toPay),
				"❌ 'To Pay' amount NOT visible");
		softAssert.assertAll();
		System.out.println("✅ All assertions checked with soft assert");

	}

}