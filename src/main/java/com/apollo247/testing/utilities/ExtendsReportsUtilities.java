package com.apollo247.testing.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendsReportsUtilities {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// Initialize report
	public static synchronized ExtentReports getReportInstance() {

		if (extent == null) {

			ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/Apollo247_ExtentReport.html");

			spark.config().setReportName("Apollo247 Automation Report");
			spark.config().setDocumentTitle("Execution Results");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("Project", "Apollo247 Automation");
			extent.setSystemInfo("Team", "QA Team");
			extent.setSystemInfo("Executed By", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
		}

		return extent;
	}

	// Create test
	public static void createTest(String testName) {
		test.set(getReportInstance().createTest(testName));
	}

	// Get test
	public static ExtentTest getTest() {
		return test.get();
	}

	// Get Info
	public static void info(String message) {
		getTest().info(message);
	}

	// Flush report
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}