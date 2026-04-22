package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.apollo247.testing.pages.ApolloProductsPage;
import com.apollo247.testing.pages.BuyMedicineCartPage;
import com.apollo247.testing.pages.BuyMedicinePage;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.VoliniPage;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.LabTestPage;
import com.apollo247.testing.pages.MyOrderPage;
import com.apollo247.testing.pages.RadiologyPage;
import com.apollo247.testing.pages.UploadPrescriptionPage;
import com.apollo247.testing.pages.HealthtoolPage;
import com.apollo247.testing.pages.LocationPage;
import com.apollo247.testing.pages.MyAppointmentPage;
import com.apollo247.testing.pages.SearchDoctorPage;
import com.apollo247.testing.pages.filterDocterPage;
import com.apollo247.testing.pages.LogoutPage;
import com.apollo247.testing.pages.ManageFamilyPage;
import com.apollo247.testing.pages.MembershipsPage;
import com.apollo247.testing.pages.MyAppointmentsPage;
import com.apollo247.testing.pages.NeedHelpPage;
import com.apollo247.testing.pages.NotificationsPage;

import com.apollo247.testing.pages.*;





public class Pages {

	public DashboardPage dashboardPage;
	public LabTestPage labTestPage;
	public UploadPrescriptionPage bookByPrescriptionPage;
	public RadiologyPage radiologyPage;
	public MyOrderPage myOrderPage;

	public SearchDoctorPage Searchdocter;
	public filterDocterPage FilterDocter;
	public MyAppointmentPage AppointmentDocter;
	public LocationPage LocationDocter;
	public HealthtoolPage HeartToolPage;
	public  HealthInsurancePage healthInsurancePage;
	public HealthInsuranceProductListings healthInsuranceProductListings;
	
	public  ManageFamilyPage manageFamilyPage;
    public  MyAppointmentsPage myappointmentsPage;
    public  MembershipsPage membershipsPage;
    public  NotificationsPage notificationsPage;
    public  NeedHelpPage needHelpPage;
    public  LogoutPage logoutPage;
    public ApolloProductsPage apolloproductsPage;
    public BuyMedicineCartPage buyMedicineCartPage;
    public BuyMedicinePage buyMedicinePage;
    public VoliniPage voliniPage;
    public Pages(WebDriver driver) {

        dashboardPage = new DashboardPage(driver);
        apolloproductsPage = new ApolloProductsPage(driver);
        buyMedicineCartPage = new BuyMedicineCartPage(driver);
        buyMedicinePage = new BuyMedicinePage(driver);
        voliniPage = new VoliniPage(driver);
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

		// SearchDocter
		Searchdocter = new SearchDoctorPage(driver);
		PageFactory.initElements(driver, Searchdocter);
		// filter
		FilterDocter = new filterDocterPage(driver);
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


		healthInsurancePage = new HealthInsurancePage(driver);
		PageFactory.initElements(driver, healthInsurancePage);
		
		healthInsuranceProductListings=new HealthInsuranceProductListings(driver);
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

        PageFactory.initElements(driver, dashboardPage);
        PageFactory.initElements(driver, apolloproductsPage);
        PageFactory.initElements(driver, buyMedicineCartPage);
        PageFactory.initElements(driver, buyMedicinePage);
        PageFactory.initElements(driver, voliniPage);
    }
}
		
		

		


