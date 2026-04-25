package com.apollo247.testing.stepdefinitions;
import java.util.List;
import java.util.Map;
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
import com.apollo247.testing.utilities.ExcelUtilities;
import com.apollo247.testing.utilities.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class search  {
	private BaseClass b;
	ExcelUtilities excel = new ExcelUtilities();
	public search(BaseClass b) {
		this.b = b;
	}
    //Find docter
	@When("user clicks the find_Docter module")
	public void user_clicks_the_find_docter_module() {
		b.getPages().dashboardPage.clickOnModule("Find Doctors");
	}
	//Docter booking
	@When("user searches for speciality")
	public void user_searches_for_speciality(io.cucumber.datatable.DataTable dataTable) {

	    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	    String speciality = data.get(0).get("speciality");

	    b.getPages().SearchDocter.searchSpeciality(speciality);
	}
	@When("user selects first available doctor")
	public void user_selects_first_available_doctor() {
		b.getPages().SearchDocter.selectDoctorByName();
	   
	}
	@When("user clicks on schedule appointment")
	public void user_clicks_on_schedule_appointment() {
	   b.getPages().SearchDocter.SelectSlot();
	}

	@When("user enters phone number")
	public void user_enters_phone_number(io.cucumber.datatable.DataTable dataTable) {

	    List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
	    String phone = data.get(0).get("phone");

	    b.getPages().SearchDocter.PhoneNumber(phone);
	}
	@Then("validate phone number and amount displayed")
	public void validate_phone_number_and_amount_displayed() {

	    // ✅ Phone validation
	    String last10 = b.getPages().SearchDocter.getLast10DigitPhone();

	    Assert.assertEquals(
	        last10.length(),
	        10,
	        "❌ Phone number is not 10 digits"
	    );

	    System.out.println("✅ Valid phone number: " + last10);

	    // ✅ Amount validation
	    String amount = b.getPages().SearchDocter.getAmountText();

	    Assert.assertTrue(
	        amount.contains("₹"),
	        "❌ Amount not displayed properly"
	    );

	    System.out.println("✅ Amount displayed: " + amount);
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
	@When("user clicks the view profile and write review {string}")
	public void user_clicks_the_view_profile_and_write_review(String reviewText) {
		 b.getPages().FilterDocter.view(); // View Profile click
		    b.getPages().FilterDocter.writeReviewFlow(reviewText); // Review flow
	}
	@Then("verify the feedback")
	public void verify_the_feedback() {
		 String actualText = b.getPages().FilterDocter.ThankYouMessage();
		 System.out.println("Actual Text: " + actualText);

		    Assert.assertTrue(
		        actualText.toLowerCase().contains("thank you for your feedback"),
		        "❌ Feedback message not displayed correctly. Actual: " + actualText
		    );
	}
	// MyAppointment
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
		Assert.assertTrue(b.getPages().AppointmentDocter.AppointmentVisible(), "Book Appointment not visible");

		System.out.println("Book Appointment is visible");

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

		@When("User enters BMI data from excel")
		public void user_enters_bmi_data_from_excel() {
			String height = excel.getExcelData("BMI", 1, 0);
			String weight = excel.getExcelData("BMI", 1, 1);
		    System.out.println("Height: " + height);
		    System.out.println("Weight: " + weight);
		    b.getPages().HeartToolPage.Height(height);
		    b.getPages().HeartToolPage.clickNextArrow();
		    b.getPages().HeartToolPage.Weight(weight);
		}
		@When("User clicks CALCULATE button")
		public void user_clicks_calculate_button() {
		    b.getPages().HeartToolPage.clickCalculate();
		}
	    
		@Then("BMI category should match expected value from excel")
		public void bmi_category_should_match_expected_value_from_excel() {
			String expectedCategory = excel.getExcelData("BMI", 1, 2);
		    String actualCategory = b.getPages().HeartToolPage.getActualCategory();

		    System.out.println("Expected: " + expectedCategory);
		    System.out.println("Actual: " + actualCategory);

		    Assert.assertEquals(
		        actualCategory.toLowerCase(),
		        expectedCategory.toLowerCase(),
		        " Defect: Expected " + expectedCategory + " but got " + actualCategory
		    );
		}
		// location
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

			Assert.assertTrue(text.contains(expectedDescription), "Description mismatch");

			System.out.println("Description validated successfully");

		}
		
		//Negative
		@When("User selects speciality {string}")
		public void user_selects_speciality(String specialist) {
		   b.getPages().NegativePage.clickSpecialdoc(specialist);
		}
		@When("User selects date {string}")
		public void user_selects_date(String datebtn) {
		  b.getPages().NegativePage.SelectDate(datebtn);
		}
		@When("User clicks on submit without location")
		public void user_clicks_on_submit_without_location() {
		    b.getPages().NegativePage.SubmitSearch();
		}
		@Then("Pincode validation message should be displayed")
		public void pincode_validation_message_should_be_displayed() {
			String msg = b.getPages().NegativePage.ErrorMsg();
	        System.out.println(msg);

	        Assert.assertTrue(
	            msg.contains("Pin-Code"),
	            "Pincode validation NOT displayed"
	        );
		}
		
		//Negative 1
		@When("user searches for the  speciality")
		public void user_searches_for_the_speciality(io.cucumber.datatable.DataTable dataTable) {
			List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

		    String speciality = data.get(0).get("speciality");

		    System.out.println("Searching speciality: " + speciality);

		    b.getPages().NegativePage.searchSpeciality(speciality);
		}

		@Then("no doctors found message should be displayed")
		public void no_doctors_found_message_should_be_displayed() {
			String actualMsg = b.getPages().NegativePage.NoDoctorsMessage();

		    System.out.println("Actual Message: " + actualMsg);

		    Assert.assertTrue(
		        actualMsg.toLowerCase().contains("couldn’t find") ||
		        actualMsg.toLowerCase().contains("no doctors"),
		        "❌ No doctors message NOT displayed"
		    );

		    System.out.println("✅ No doctors found message validated");
		}


}
