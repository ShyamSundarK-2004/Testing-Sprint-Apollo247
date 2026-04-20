package com.apollo247.testing.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.apollo247.testing.pages.DashboardPage;
import com.apollo247.testing.pages.LabTestPage;
import com.apollo247.testing.pages.RadiologyPage;
import com.apollo247.testing.pages.UploadPrescriptionPage;

public class Pages {

	public DashboardPage dashboardPage;
	public LabTestPage labTestPage;
	public UploadPrescriptionPage bookByPrescriptionPage;
	public RadiologyPage radiologyPage;

	// all the pages are initialized with the driver object
	public Pages(WebDriver driver) {

		// dashboard page driver initialization
		dashboardPage = new DashboardPage(driver);
		PageFactory.initElements(driver, dashboardPage);

		// labtest page driver initialization
		labTestPage = PageFactory.initElements(driver, LabTestPage.class);

		// upload_prescription page driver initialization
		bookByPrescriptionPage = new UploadPrescriptionPage(driver);
		PageFactory.initElements(driver, bookByPrescriptionPage);

		// radiology page driver initialization
		radiologyPage = new RadiologyPage(driver);
		PageFactory.initElements(driver, radiologyPage);
	}
}
