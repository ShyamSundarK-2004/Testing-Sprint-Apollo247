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
import com.apollo247.testing.pages.InvalidSearchPage;
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
    public ApolloProductsPage apolloproductsPage;
    public BuyMedicineCartPage buyMedicineCartPage;
    public BuyMedicinePage buyMedicinePage;
    public VoliniPage voliniPage;
    public InvalidSearchPage invalidSearchPage;

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
    public HealthInsurance_PolicyReview healthInsurance_PolicyReview;
    public HealthInsurance_InsuranceForm healthInsurance_InsuranceForm;

    public ManageFamilyPage manageFamilyPage;
    public MyAppointmentsPage myappointmentsPage;
    public MembershipsPage membershipsPage;
    public NotificationsPage notificationsPage;
    public NeedHelpPage needHelpPage;
    public LogoutPage logoutPage;

    public SearchDoctorPage Searchdoctor;
    public filterDoctorPage FilterDoctor;
    public MyAppointmentPage AppointmentDoctor;
    public LocationPage LocationDoctor;

    public HealthtoolPage HeartToolPage;
    public NegativeScenario NegativePage;

    public Pages(WebDriver driver) {

        dashboardPage = new DashboardPage(driver);
        apolloproductsPage = new ApolloProductsPage(driver);
        buyMedicineCartPage = new BuyMedicineCartPage(driver);
        buyMedicinePage = new BuyMedicinePage(driver);
        voliniPage = new VoliniPage(driver);
        invalidSearchPage = new InvalidSearchPage(driver);

        Searchdoctor = new SearchDoctorPage(driver);
        FilterDoctor = new filterDoctorPage(driver);
        AppointmentDoctor = new MyAppointmentPage(driver);
        LocationDoctor = new LocationPage(driver);

        HeartToolPage = new HealthtoolPage(driver);
        NegativePage = new NegativeScenario(driver);

        healthInsurancePage = new HealthInsurancePage(driver);
        healthInsuranceProductListings = new HealthInsuranceProductListings(driver);
        healthInsurance_PolicyReview = new HealthInsurance_PolicyReview(driver);
        healthInsurance_InsuranceForm = new HealthInsurance_InsuranceForm(driver);

        manageFamilyPage = new ManageFamilyPage(driver);
        myappointmentsPage = new MyAppointmentsPage(driver);
        membershipsPage = new MembershipsPage(driver);
        notificationsPage = new NotificationsPage(driver);
        needHelpPage = new NeedHelpPage(driver);
        logoutPage = new LogoutPage(driver);

        labTestPage = new LabTestPage(driver);
        bookByPrescriptionPage = new UploadPrescriptionPage(driver);
        radiologyPage = new RadiologyPage(driver);
        myOrderPage = new MyOrderPage(driver);
        searchResultPage = new SearchResultPage(driver);
        testPage = new TestPage(driver);
        patientDetailsPage = new PatientDetailsPage(driver);
        testCartPage = new TestCartPage(driver);

        PageFactory.initElements(driver, dashboardPage);
        PageFactory.initElements(driver, apolloproductsPage);
        PageFactory.initElements(driver, buyMedicineCartPage);
        PageFactory.initElements(driver, buyMedicinePage);
        PageFactory.initElements(driver, voliniPage);
        PageFactory.initElements(driver, invalidSearchPage);

        PageFactory.initElements(driver, Searchdoctor);
        PageFactory.initElements(driver, FilterDoctor);
        PageFactory.initElements(driver, AppointmentDoctor);
        PageFactory.initElements(driver, LocationDoctor);

        PageFactory.initElements(driver, HeartToolPage);
        PageFactory.initElements(driver, NegativePage);

        PageFactory.initElements(driver, healthInsurancePage);
        PageFactory.initElements(driver, healthInsuranceProductListings);
        PageFactory.initElements(driver, healthInsurance_PolicyReview);
        PageFactory.initElements(driver, healthInsurance_InsuranceForm);

        PageFactory.initElements(driver, manageFamilyPage);
        PageFactory.initElements(driver, myappointmentsPage);
        PageFactory.initElements(driver, membershipsPage);
        PageFactory.initElements(driver, notificationsPage);
        PageFactory.initElements(driver, needHelpPage);
        PageFactory.initElements(driver, logoutPage);

        PageFactory.initElements(driver, labTestPage);
        PageFactory.initElements(driver, bookByPrescriptionPage);
        PageFactory.initElements(driver, radiologyPage);
        PageFactory.initElements(driver, myOrderPage);
        PageFactory.initElements(driver, searchResultPage);
        PageFactory.initElements(driver, testPage);
        PageFactory.initElements(driver, patientDetailsPage);
        PageFactory.initElements(driver, testCartPage);
    }
}