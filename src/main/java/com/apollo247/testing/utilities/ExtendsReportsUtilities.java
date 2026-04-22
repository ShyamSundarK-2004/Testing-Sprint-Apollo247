package com.apollo247.testing.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendsReportsUtilities {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// Initialize report
	public static ExtentReports getReportInstance() {

		if (extent == null) {

			ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/Apollo247_ExtentReport.html");
			spark.config().setReportName("Apollo247 Automation Report");
			spark.config().setDocumentTitle("Execution Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			// TEAM FRIENDLY INFO
			extent.setSystemInfo("Project", "Apollo247 Automation");
			extent.setSystemInfo("Team", "QA Team");
			extent.setSystemInfo("Executed By", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
		}

		return extent;
	}

	// Create test
	public static void createTest(String testName) {
		ExtentTest extentTest = getReportInstance().createTest(testName);
		test.set(extentTest);
	}

	// Get test
	public static ExtentTest getTest() {
		return test.get();
	}

	// Flush report
	public static void flushReport() {
		getReportInstance().flush();
	}
}