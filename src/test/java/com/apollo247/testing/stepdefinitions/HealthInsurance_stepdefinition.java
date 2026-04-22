package com.apollo247.testing.stepdefinitions;

import org.openqa.selenium.By;
import org.testng.Assert;

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

}
