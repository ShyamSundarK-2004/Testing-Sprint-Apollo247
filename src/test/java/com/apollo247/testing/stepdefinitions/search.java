package com.apollo247.testing.stepdefinitions;

import java.time.Duration;

import java.util.List;
import java.util.Map;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.apollo247.testing.utilities.BaseClass;
import com.apollo247.testing.utilities.Pages;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class search  {
	private BaseClass b;

	public search(BaseClass b) {
		this.b = b;
	}
	@When("user clicks the find_Docter module")
	public void user_clicks_the_find_docter_module() {
	      b.getPages().dashboardPage.clickOnModule("Find Doctors");
	}

	@When("user searches for {string} specialist in {string} on date {string}")
	public void searchDoctor(String spec, String city, String date) {

		b.getPages().Searchdocter.SearchDoctor(spec, city, date);
	}

	@When("user selects doctor")
	public void selectDoctor() {
		b.getPages().Searchdocter.selectFirstDoctor();
	}

	@When("user selects available slot")
	public void user_selects_available_slot() {
		b.getPages().Searchdocter.SelectSlot();
	}

	@When("user adds new patient with details")
	public void user_adds_new_patient_with_details(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

		String fName = data.get(0).get("firstName");
		String lName = data.get(0).get("lastName");
		String mail = data.get(0).get("email");
		String year = data.get(0).get("year");
		String month = data.get(0).get("month");
		String day = data.get(0).get("day");
		b.getPages().Searchdocter.AddPatient(fName, lName, mail, year, month, day);
	}

	@Then("booking details should be displayed")
	public void booking_details_should_be_displayed() {

		String url = b.getDriver().getCurrentUrl();

		if (url.contains("appointment")) {
			System.out.println("Booking successful");
		} else {
			throw new AssertionError("Booking failed");
		}
	   
	}
	//Filter
	@Given("User click the general Physician")
	public void user_click_the_general_physician() {
		b.getPages().FilterDocter.ClickGeneral();
	}
	@When("user applies sorting as {string}")
	public void user_applies_sorting_as(String price) {
		b.getPages().FilterDocter.Relevance();
	}
	@When("user filters doctors by experience {string}")
	public void user_filters_doctors_by_experience(String experience) {
		b.getPages().FilterDocter.getExperience().click();
	}
	@When("user filters doctors by language {string}")
	public void user_filters_doctors_by_language(String Lang) {
		b.getPages().FilterDocter.getLanguage().click();
	    
	}
	@When("user clicks on first displayed docter")
	public void user_clicks_on_first_displayed_docter() {
		b.getPages().FilterDocter.clickFirstDoctor();
	  
	}
	@When("select the slot and continue to book")
	public void select_the_slot_and_continue_to_book() {
		b.getPages().FilterDocter.Clickconbtn();
	}
	@Then("verify the booking details")
	public void verify_the_booking_details() {
		    String url = b.getDriver().getCurrentUrl();

		    if(url.contains("appointment")) {
		        System.out.println("Booking successful");
		    } else {
		        throw new AssertionError("Booking failed");
		    }
	    
	}
	//MyAppointment
	@When("User navigates to My Appointments and clicks View All")
	public void user_navigates_to_my_appointments_and_clicks_view_all() {
		b.getPages().AppointmentDocter.view();
	}
	@When("User clicks on Rebook for a doctor")
	public void user_clicks_on_rebook_for_a_doctor() {
		b.getPages().AppointmentDocter.Rebook();
	}
	@When("User clicks Continue")
	public void user_clicks_continue() {
		b.getPages().AppointmentDocter.getConbtn();
		
	    
	}
	@When("User click ChangeBtn to Change the patient and click proceed")
	public void user_click_change_btn_to_change_the_patient_and_click_proceed() {
		b.getPages().AppointmentDocter.ChangePatient();
	    
	}
	@Then("User should see Book Appointment option")
	public void user_should_see_book_appointment_option() {
		 Assert.assertTrue(b.getPages().AppointmentDocter.AppointmentVisible(),
		            "Book Appointment not visible");

		    System.out.println("Book Appointment is visible");
	
	}
	//location
	@When("User selects location and specialization")
	public void user_selects_location_and_specialization() {
		b.getPages().LocationDocter.selectLocation();
	}
	@When("User sorts by Most Liked")
	public void user_sorts_by_most_liked() {
		b.getPages().LocationDocter.sortByMostLiked();
	}
	@When("User opens doctor description {string}")
	public void user_opens_doctor_description(String doctername) {
	    String name = b.getPages().LocationDocter.openDoctor(doctername);
	    System.out.println("Doctor Name: " + name);
	}
	@Then("Doctor description should be validated {string}")
	public void doctor_description_should_be_validated(String expectedDescription) {
		String text = b.getPages().LocationDocter.getDescription();

	    System.out.println("Actual Description: " + text);

	    Assert.assertTrue(
	        text.contains(expectedDescription),
	        "Description mismatch"
	    );

	    System.out.println("Description validated successfully");
	        
	}
	//hearttool
	@When("User navigates to Health Tools page")
	public void user_navigates_to_health_tools_page() {
		b.getPages().HeartToolPage.clickHealthToolCard();
	    
	}
	@When("User clicks CALCULATE for Body Mass Index")
	public void user_clicks_calculate_for_body_mass_index() {
		b.getPages().HeartToolPage.clickBMICalculate();
	}
	@When("User selects gender as Female")
	public void user_selects_gender_as_female() {
		b.getPages().HeartToolPage.Female();
	
	}
	@When("User navigates to height input")
	public void user_navigates_to_height_input() {
		b.getPages().HeartToolPage.clickNavigate();
	}
	@When("User enters height as {string}")
	public void user_enters_height_as(String height) {
		b.getPages().HeartToolPage.Height(height);
	
	}
	@When("User clicks next arrow")
	public void user_clicks_next_arrow() {
		b.getPages().HeartToolPage.clickNextArrow();
	}
	@When("User enters weight as {string}")
	public void user_enters_weight_as(String weight) {
		b.getPages().HeartToolPage.Weight(weight);
	}
	@When("User clicks CALCULATE button")
	public void user_clicks_calculate_button() {
		b.getPages().HeartToolPage.clickCalculate();;
	}
	@Then("BMI result should be displayed")
	public void bmi_result_should_be_displayed() {
		Assert.assertTrue(b.getPages().HeartToolPage.isBMIResultDisplayed());
	}
	@Then("BMI category should be {string} or {string} or {string}")
	public void bmi_category_should_be_or_or(String normal, String overweight, String obese) {

	    String actual = b.getPages().HeartToolPage.getBMICategory();

	    Assert.assertTrue(
	        actual.contains(normal) || 
	        actual.contains(overweight) || 
	        actual.contains(obese)
	    );
	}
	



}

