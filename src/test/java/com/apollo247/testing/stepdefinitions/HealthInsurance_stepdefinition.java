package com.apollo247.testing.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.asserts.SoftAssert;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.SessionManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HealthInsurance_stepdefinition {
	BaseClass b;
	public HealthInsurance_stepdefinition(BaseClass b) {
		this.b=b;
	}
	
	

@Given("User navigates to Health Insurance page and enter pincode {string}")
public void user_navigates_to_health_insurance_page_and_enter_pincode(String pincode) {
	b.getPages().dashboardPage.clickonHealthInsuranceModule();
	
	
	try {
		SessionManager.switchToDomain(b.getDriver(), "https://www.apollo247insurance.com/");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	b.getPages().healthInsurancePage.clickCancelSelectLocation();
	b.getPages().dashboardPage.clickOnModule("Health Insurance");
	b.getPages().healthInsurancePage.performEnterPinCode(pincode);

	System.out.println("Enter pincode");

	
    
}
@When("User selects {string} and {string} at the age {string} as members")
public void user_selects_and_at_the_age_as_members(String gender, String m1, String m1Age) {
	//b.getPages().healthInsurancePage.performEnterPinCode("601201");
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
	
	Assert.assertTrue(b.getPages().healthInsuranceProductListings.viewPlanHeader().isDisplayed(), "View Plans header not displayed");
	Assert.assertTrue(b.getPages().healthInsuranceProductListings.viewNumberOfPlans().size() > 0, "No plans found on page");
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
    String actualMessage =b.getPages().healthInsurancePage.errorMessageNoMemeberSelected();
    Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch");
    System.out.println("Validated successfully");
}


@When("User selects Gender {string} {string} at age {string}, {string} at age {string}, and {string}  at age {string} as members")
public void user_selects_gender_at_age_at_age_and_at_age_as_members(String gender, String m1, String m1Age,String m2, String m2Age, String m3, String m3Age) {
	b.getPages().healthInsurancePage.selectGender(gender);
	b.getPages().healthInsurancePage.unselectMember();
	b.getPages().healthInsurancePage.selectMember(m1, m1Age);
	b.getPages().healthInsurancePage.selectMember(m2, m2Age);
	b.getPages().healthInsurancePage.selectMember(m3, m3Age);
}
// Implemented user clicks on "View Plans" in another stepdefinition file
// so clicked "View plans" button

@Then("Family insurance plans should be displayed correctly")
public void family_insurance_plans_should_be_displayed_correctly() {
	Assert.assertTrue(b.getPages().healthInsuranceProductListings.isNoPlansMessageDisplayed(),"'No plans found' message not displayed correctly");
    Assert.assertTrue(b.getPages().healthInsuranceProductListings.isPlansAvailable(),"Plans are not displayed properly");
	System.out.println("Validated");
}

//@filtering 
//User selected Male self at age 22

@When("User applies coverage filter between {string}")
public void user_applies_coverage_filter_between(String coverageAmount) {
	//b.getPages().healthInsuranceProductListings.clickFilter();
	b.getPages().healthInsuranceProductListings.coverageAmount(coverageAmount);
	System.out.println("coverage amt selected");
	
    
}
@When("User selects room rent type as {string}")
public void user_selects_room_rent_type_as(String roomRentType) {
	//b.getPages().healthInsuranceProductListings.clickFilter();
	b.getPages().healthInsuranceProductListings.roomRentType(roomRentType);
	System.out.println("roomrent type selected");
    
}
@When("User sorts plans by {string}")
public void user_sorts_plans_by(String plan) {
	b.getPages().healthInsuranceProductListings.sortByPlans(plan);
	System.out.println("sort plans selected");

}
@Then("Plans should be displayed based on applied filters coverage amount {string} and {string}")
public void plans_should_be_displayed_based_on_applied_filters_coverage_amount_and(String coverageAmt, String planType) {

    boolean isValid = b.getPages().healthInsuranceProductListings.isMatch(coverageAmt);
    Assert.assertTrue(isValid, "Coverage filter validation failed");
    Assert.assertTrue(b.getPages().healthInsuranceProductListings.isTextMatching(planType),"Text does not match!");
    System.out.println("amt and plans validated");
    
    
}

// Insurance Policy form validate inputs
@When("User clicks proceeds with plan customization")
public void user_clicks_proceeds_with_plan_customization() {
	  b.getPages().healthInsuranceProductListings.clickProceedsPlan();
	  b.getPages().healthInsurance_PolicyReview.clickPolicyProceedButton();
}
@When("User completes step1 Member Details form {string} {string} {string} {string} {string} {string}")
public void user_completes_step1_member_details_form(String firstName, String lastName, String DOB, String feet, String inch, String weight) {
	b.getPages().healthInsurance_InsuranceForm.fillMemberDetails(firstName, lastName, DOB, feet, inch, weight);
	
   }
@When("User completes  step2 Medical Questions form {string} {string} {string} {string} {string}")
public void user_completes_step2_medical_questions_form(String opt1, String opt2, String opt3, String opt4, String opt5) {
	b.getPages().healthInsurance_InsuranceForm.fillMedicalQuestions(opt1, opt2, opt3, opt4, opt5);
    
}
@When("User completes step3 Proposer Details form {string} {string} {string} {string} {string}")
public void user_completes_step3_proposer_details_form(String emailId, String phNO, String emphNO, String proposerFN, String PANno ) {
	b.getPages().healthInsurance_InsuranceForm.fillProposerDetailForm(emailId, phNO, emphNO, proposerFN, PANno);
    
}
@When("User completes step4 KYC Verification {string} {string} upload document")
public void user_completes_step4_kyc_verification_upload_document(String type, String idNO) {
	b.getPages().healthInsurance_InsuranceForm.fillKYCVerificationForm(type, idNO);
    
}
@When("User completes step5 address proof {string} {string} upload document")
public void user_completes_step5_address_proof_upload_document(String type, String idNO) throws InterruptedException {
	b.getPages().healthInsurance_InsuranceForm.fillAddressProofForm(type, idNO);
    }
@When("User completes step6 Address Details {string} {string} {string}")
public void user_completes_step6_address_details(String flatNo, String location, String pincode) throws InterruptedException {
	b.getPages().healthInsurance_InsuranceForm.fillAddressDetails(flatNo, location, pincode);
	
    }
@When("User completes step7 Nomiee Selection {string} {string} {string} {string} {string} {string} {string} {string}")
public void user_completes_step7_nomiee_selection(String nomineeFN, String nomineeLN, String noRelation, String noDOB, String apFN, String apLN, String apDOB, String apRealtion) {
	b.getPages().healthInsurance_InsuranceForm.fillNomineeSelection(nomineeFN, nomineeLN, noRelation, noDOB, apFN,apLN,apDOB,apRealtion);
    }
@When("User completes step8 Bank Account Details {string} {string} {string}")
public void user_completes_step8_bank_account_details(String accNO, String accType, String IFSCcode) {
	b.getPages().healthInsurance_InsuranceForm.fillBankAccountDetails(accNO, accType, IFSCcode);
    }
@When("User completes all the forms and clicks {string}")
public void user_completes_all_the_forms_and_clicks(String nextbtn) {
	b.getPages().healthInsurance_InsuranceForm.clickNextBtn(nextbtn);
	b.getPages().healthInsurance_InsuranceForm.clickNextBtn(nextbtn);
    }
@When("User accepts Terms and Conditions")
public void user_accepts_terms_and_conditions() {
	b.getPages().healthInsurance_InsuranceForm.acceptsTC();
    }

@Then("User reviews policy details {string}")
public void user_reviews_policy_details(String memberName) {
	SoftAssert softAssert = new SoftAssert();
    boolean isValid = b.getPages().healthInsurance_InsuranceForm.reviewPolicyDetails(memberName);
    softAssert.assertTrue(isValid, "Policy details review failed for member: " + memberName);
    softAssert.assertAll();
}
@When("User clicks on policy form {string}")
public void user_clicks_on_policy_form(String BuyNow) {
	b.getPages().healthInsurance_InsuranceForm.policyBUY_NOW(BuyNow);
	b.getPages().healthInsurance_InsuranceForm.policyBUY_NOW(BuyNow);
    
}
@Then("The Payment Options page should be displayed with {string} {string} {string} and validating the policy")
public void the_payment_options_page_should_be_displayed_with_and_validating_the_policy(String payment, String paymentOpt, String topayamt) throws IOException {
	
	SoftAssert softAssert = new SoftAssert();

    boolean isPaymentPage = b.getPages()
            .healthInsurance_InsuranceForm
            .verifyPaymentPage(payment);

    boolean isPaymentOptionVisible = b.getPages()
            .healthInsurance_InsuranceForm
            .verifyPaymentOption(paymentOpt);

    boolean isToPayVisible = b.getPages()
            .healthInsurance_InsuranceForm
            .verifyAmtVisible(topayamt);

    // ✅ Assertions
    softAssert.assertTrue(isPaymentPage,
            "❌ Payment page NOT loaded (URL or UI mismatch)");

    softAssert.assertTrue(isPaymentOptionVisible,
            "❌ 'Payment Options' text NOT visible");

    softAssert.assertTrue(isToPayVisible,
            "❌ 'To Pay' amount NOT visible");

    // ✅ Important (collect all failures)
    softAssert.assertAll();
	System.out.println("✅ All assertions checked with soft assert");

}



}