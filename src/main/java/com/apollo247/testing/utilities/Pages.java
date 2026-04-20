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

    public Pages(WebDriver driver) {

        dashboardPage = new DashboardPage(driver);
        PageFactory.initElements(driver, dashboardPage);



        
        manageFamilyPage = new ManageFamilyPage(driver);
        myappointmentsPage = new MyAppointmentsPage(driver);
        membershipsPage = new MembershipsPage(driver);
        notificationsPage = new NotificationsPage(driver);
        needHelpPage = new NeedHelpPage(driver);
        logoutPage = new LogoutPage(driver);

        PageFactory.initElements(driver, manageFamilyPage);
        PageFactory.initElements(driver, myappointmentsPage);
        PageFactory.initElements(driver, membershipsPage);
        PageFactory.initElements(driver, notificationsPage);
        PageFactory.initElements(driver, needHelpPage);
        PageFactory.initElements(driver, logoutPage);
    }
}