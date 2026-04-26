package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.apollo247.testing.pages.ApolloProductsPage;
import com.apollo247.testing.pages.BuyMedicineCartPage;
import com.apollo247.testing.pages.BuyMedicinePage;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.HealthInsurancePage;
import com.apollo247.testing.pages.HealthInsuranceProductListings;
import com.apollo247.testing.pages.HealthInsurance_InsuranceForm;
import com.apollo247.testing.pages.HealthInsurance_PolicyReview;
import com.apollo247.testing.pages.HealthtoolPage;
import com.apollo247.testing.pages.LabTestPage;
import com.apollo247.testing.pages.LocationPage;
import com.apollo247.testing.pages.LogoutPage;
import com.apollo247.testing.pages.ManageFamilyPage;
import com.apollo247.testing.pages.MembershipsPage;
import com.apollo247.testing.pages.MyAppointmentPage;
import com.apollo247.testing.pages.MyAppointmentsPage;
import com.apollo247.testing.pages.MyOrderPage;
import com.apollo247.testing.pages.NeedHelpPage;
import com.apollo247.testing.pages.NegativeScenario;
import com.apollo247.testing.pages.NotificationsPage;
import com.apollo247.testing.pages.PatientDetailsPage;
import com.apollo247.testing.pages.RadiologyPage;
import com.apollo247.testing.pages.SearchDoctorPage;
import com.apollo247.testing.pages.SearchResultPage;
import com.apollo247.testing.pages.TestCartPage;
import com.apollo247.testing.pages.TestPage;
import com.apollo247.testing.pages.UploadPrescriptionPage;
import com.apollo247.testing.pages.VoliniPage;
import com.apollo247.testing.pages.filterDoctorPage;

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
	public HealthInsurancePage healthInsurancePage;
	public HealthInsuranceProductListings healthInsuranceProductListings;
	public ManageFamilyPage manageFamilyPage;
	public MyAppointmentsPage myappointmentsPage;
	public MembershipsPage membershipsPage;
	public NotificationsPage notificationsPage;
	public NeedHelpPage needHelpPage;
	public LogoutPage logoutPage;
	public ApolloProductsPage apolloproductsPage;
	public BuyMedicineCartPage buyMedicineCartPage;
	public BuyMedicinePage buyMedicinePage;
	public VoliniPage voliniPage;
	public SearchDoctorPage SearchDocter;
	public filterDoctorPage FilterDocter;
	public MyAppointmentPage AppointmentDocter;
	public LocationPage LocationDocter;
	public HealthtoolPage HeartToolPage;
	public NegativeScenario NegativePage;

	public HealthInsurance_PolicyReview healthInsurance_PolicyReview;
	public HealthInsurance_InsuranceForm healthInsurance_InsuranceForm;

	// all the pages are initialized with the driver object

	public Pages(WebDriver driver) {

		// initializing driver for dashboard page
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);

		apolloproductsPage = new ApolloProductsPage(driver);
		PageFactory.initElements(driver, apolloproductsPage);

		buyMedicineCartPage = new BuyMedicineCartPage(driver);
		PageFactory.initElements(driver, buyMedicineCartPage);

		buyMedicinePage = new BuyMedicinePage(driver);
		PageFactory.initElements(driver, buyMedicinePage);

		voliniPage = new VoliniPage(driver);
		PageFactory.initElements(driver, voliniPage);

		// SearchDocter
		SearchDocter = new SearchDoctorPage(driver);
		PageFactory.initElements(driver, SearchDocter);
		// filter
		FilterDocter = new filterDoctorPage(driver);
		PageFactory.initElements(driver, FilterDocter);
		// Appointment
		AppointmentDocter = new MyAppointmentPage(driver);
		PageFactory.initElements(driver, AppointmentDocter);
		// Location
		LocationDocter = new LocationPage(driver);
		PageFactory.initElements(driver, LocationDocter);
		// Healthtool
		HeartToolPage = new HealthtoolPage(driver);
		PageFactory.initElements(driver, HeartToolPage);
		// Negative
		NegativePage = new NegativeScenario(driver);
		PageFactory.initElements(driver, NegativePage);

		healthInsurancePage = new HealthInsurancePage(driver);
		PageFactory.initElements(driver, healthInsurancePage);

		healthInsuranceProductListings = new HealthInsuranceProductListings(driver);
		PageFactory.initElements(driver, healthInsuranceProductListings);

		manageFamilyPage = new ManageFamilyPage(driver);
		PageFactory.initElements(driver, manageFamilyPage);

		myappointmentsPage = new MyAppointmentsPage(driver);
		PageFactory.initElements(driver, myappointmentsPage);

		membershipsPage = new MembershipsPage(driver);
		PageFactory.initElements(driver, membershipsPage);

		notificationsPage = new NotificationsPage(driver);
		PageFactory.initElements(driver, notificationsPage);

		needHelpPage = new NeedHelpPage(driver);
		PageFactory.initElements(driver, needHelpPage);

		logoutPage = new LogoutPage(driver);
		PageFactory.initElements(driver, logoutPage);

		healthInsurance_PolicyReview = new HealthInsurance_PolicyReview(driver);
		PageFactory.initElements(driver, healthInsurance_PolicyReview);

		healthInsurance_InsuranceForm = new HealthInsurance_InsuranceForm(driver);
		PageFactory.initElements(driver, healthInsurance_InsuranceForm);

		// ====== LABTEST MODULE PAGES ======

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
