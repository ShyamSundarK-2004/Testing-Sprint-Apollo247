package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.apollo247.testing.pages.*;

public class Pages {

	public DashboardPage dashboardPage;
	public  HealthInsurancePage healthInsurancePage;
	public HealthInsuranceProductListings healthInsuranceProductListings;
	public HealthInsurance_PolicyReview healthInsurance_PolicyReview;
	public HealthInsurance_InsuranceForm healthInsurance_InsuranceForm;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);


		healthInsurancePage = new HealthInsurancePage(driver);
		PageFactory.initElements(driver, healthInsurancePage);
		
		healthInsuranceProductListings=new HealthInsuranceProductListings(driver);
		PageFactory.initElements(driver, healthInsuranceProductListings);
		
		healthInsurance_PolicyReview=new HealthInsurance_PolicyReview(driver);
		PageFactory.initElements(driver, healthInsurance_PolicyReview);
		
		healthInsurance_InsuranceForm =new HealthInsurance_InsuranceForm(driver);
		PageFactory.initElements(driver, healthInsurance_InsuranceForm);
		
		
		
		

		

		

	}
}
