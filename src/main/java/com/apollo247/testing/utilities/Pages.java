package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.LogoutPage;
import com.apollo247.testing.pages.ManageFamilyPage;
import com.apollo247.testing.pages.MembershipsPage;
import com.apollo247.testing.pages.MyAppointmentsPage;
import com.apollo247.testing.pages.NeedHelpPage;
import com.apollo247.testing.pages.NotificationsPage;



public class Pages {

	public DashboardPage dashboardPage;
	
	
	public  ManageFamilyPage manageFamilyPage;
    public  MyAppointmentsPage myappointmentsPage;
    public  MembershipsPage membershipsPage;
    public  NotificationsPage notificationsPage;
    public  NeedHelpPage needHelpPage;
    public  LogoutPage logoutPage;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);

			
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


	}
}
