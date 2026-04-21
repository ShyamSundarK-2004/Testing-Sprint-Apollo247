package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.HealthtoolPage;
import com.apollo247.testing.pages.LocationPage;
import com.apollo247.testing.pages.MyAppointmentPage;
import com.apollo247.testing.pages.SearchDoctorPage;
import com.apollo247.testing.pages.filterDocterPage;

public class Pages {

	public  DashboardPage dashboardPage;
	public  SearchDoctorPage Searchdocter;
	public  filterDocterPage FilterDocter;
	public  MyAppointmentPage AppointmentDocter;
	public  LocationPage LocationDocter;
	public  HealthtoolPage HeartToolPage;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);
		//SearchDocter
		Searchdocter=new SearchDoctorPage(driver);
		PageFactory.initElements(driver, Searchdocter);
		//filter
		FilterDocter=new filterDocterPage(driver);
		PageFactory.initElements(driver, FilterDocter);
		//Appointment
		AppointmentDocter=new MyAppointmentPage(driver);
		PageFactory.initElements(driver, AppointmentDocter);
		//Location
		LocationDocter=new LocationPage(driver);
		PageFactory.initElements(driver, LocationDocter);
		//Healthtool
		HeartToolPage=new HealthtoolPage(driver);
		PageFactory.initElements(driver, HeartToolPage);
		
		
	}
}
