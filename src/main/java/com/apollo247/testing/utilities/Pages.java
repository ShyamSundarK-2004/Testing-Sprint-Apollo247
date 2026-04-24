package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.LabTestPage;
import com.apollo247.testing.pages.MyOrderPage;
import com.apollo247.testing.pages.PatientDetailsPage;
import com.apollo247.testing.pages.RadiologyPage;
import com.apollo247.testing.pages.SearchResultPage;
import com.apollo247.testing.pages.TestCartPage;
import com.apollo247.testing.pages.TestPage;
import com.apollo247.testing.pages.UploadPrescriptionPage;

public class Pages {

	public DashboardPage dashboardPage;
	public LabTestPage labTestPage;
	public UploadPrescriptionPage bookByPrescriptionPage;
	public RadiologyPage radiologyPage;
	public MyOrderPage myOrderPage;
	public SearchResultPage searchResultPage;
	public TestPage testPage;
	public PatientDetailsPage patientDetailsPage;
	public TestCartPage testCartPage;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);

		// labtest page driver initialization
		labTestPage = new LabTestPage(driver);
		PageFactory.initElements(driver, labTestPage);

		// upload_prescription page driver initialization
		bookByPrescriptionPage = new UploadPrescriptionPage(driver);
		PageFactory.initElements(driver, bookByPrescriptionPage);

		// radiology page driver initialization
		radiologyPage = new RadiologyPage(driver);
		PageFactory.initElements(driver, radiologyPage);

		// orders page driver initialization
		myOrderPage = new MyOrderPage(driver);
		PageFactory.initElements(driver, myOrderPage);

		// search results page driver initialization
		searchResultPage = new SearchResultPage(driver);
		PageFactory.initElements(driver, searchResultPage);

		// labtest cart page driver initialization
		testPage = new TestPage(driver);
		PageFactory.initElements(driver, testPage);

		// patient details page driver initialization
		patientDetailsPage = new PatientDetailsPage(driver);
		PageFactory.initElements(driver, patientDetailsPage);

		// test cart page driver initialization
		testCartPage = new TestCartPage(driver);
		PageFactory.initElements(driver, testCartPage);
	}
}
