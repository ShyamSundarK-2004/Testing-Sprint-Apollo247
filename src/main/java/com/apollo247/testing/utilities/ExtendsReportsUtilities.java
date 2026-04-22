package com.apollo247.testing.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendsReportsUtilities {

	private static ExtentReports extent;

	// 🔥 Thread-safe (important for parallel execution)
	private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> stepNode = new ThreadLocal<>();

	// ================== REPORT INSTANCE ==================

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

	// ================== SCENARIO ==================

	public static synchronized void createTest(String scenarioName) {

		ExtentTest test = getReportInstance().createTest(scenarioName);
		scenarioTest.set(test);

		// clear previous step (important)
		stepNode.set(null);
	}

	public static ExtentTest getTest() {
		return scenarioTest.get();
	}

	// ================== STEP NODE ==================

	public static void createStep(String stepText) {

		ExtentTest parent = scenarioTest.get();

		if (parent != null) {
			ExtentTest node = parent.createNode(stepText);
			stepNode.set(node);
		}
	}

	// ================== LOGGING ==================

	public static void pass(String message) {

		ExtentTest node = stepNode.get();

		if (node != null) {
			node.pass(message);
		} else {
			scenarioTest.get().pass(message);
		}
	}

	public static void fail(String message) {

		ExtentTest node = stepNode.get();

		if (node != null) {
			node.fail(message);
		} else {
			scenarioTest.get().fail(message);
		}
	}

	public static void info(String message) {

		ExtentTest node = stepNode.get();

		if (node != null) {
			node.info(message);
		} else {
			scenarioTest.get().info(message);
		}
	}

	public static void logStep(String keyword, String stepText) {

		String fullStep = "<b>" + keyword + "</b> " + stepText;

		createStep(fullStep);
	}

	// ================== SCREENSHOT ==================

	public static void attachScreenshot(String path) {

		try {
			ExtentTest node = stepNode.get();

			if (node != null) {
				node.addScreenCaptureFromPath(path);
			} else {
				scenarioTest.get().addScreenCaptureFromPath(path);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ================== FLUSH ==================

	public static void flushReport() {

		if (extent != null) {
			extent.flush();
		}
	}
}