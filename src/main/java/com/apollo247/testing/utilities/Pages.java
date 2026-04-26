package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.HealthtoolPage;
import com.apollo247.testing.pages.LocationPage;
import com.apollo247.testing.pages.MyAppointmentPage;
import com.apollo247.testing.pages.NegativeScenario;
import com.apollo247.testing.pages.SearchDoctorPage;
import com.apollo247.testing.pages.filterDoctorPage;

public class Pages {

	public  DashboardPage dashboardPage;
	public  SearchDoctorPage Searchdoctor;
	public  filterDoctorPage FilterDoctor;
	public  MyAppointmentPage AppointmentDoctor;
	public  LocationPage LocationDoctor;
	public  HealthtoolPage HeartToolPage;
	public  NegativeScenario NegativePage;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);
		//SearchDocter
		Searchdoctor=new SearchDoctorPage(driver);
		PageFactory.initElements(driver, Searchdoctor);
		//filter
		FilterDoctor=new filterDoctorPage(driver);
		PageFactory.initElements(driver, FilterDoctor);
		//Appointment
		AppointmentDoctor=new MyAppointmentPage(driver);
		PageFactory.initElements(driver, AppointmentDoctor);
		//Location
		LocationDoctor=new LocationPage(driver);
		PageFactory.initElements(driver, LocationDoctor);
		//Healthtool
		HeartToolPage=new HealthtoolPage(driver);
		PageFactory.initElements(driver, HeartToolPage);
		NegativePage=new NegativeScenario(driver);
		PageFactory.initElements(driver, NegativePage);
		
		
	}
}
