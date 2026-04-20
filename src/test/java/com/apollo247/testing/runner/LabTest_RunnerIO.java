package com.apollo247.testing.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/apollo247/testing/features/LabTest.feature", glue = "com.apollo247.testing.stepdefinitions", dryRun = true, plugin = {
		"pretty", "html:Reports/Lab_Test-CucumberReport.html" }, tags = "@searchScenario")
public class LabTest_RunnerIO extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
