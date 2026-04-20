package com.apollo247.testing.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = {"./src/test/java/com/apollo247/testing/features"},
	    glue = {"com.apollo247.testing.stepdefinitions"},
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports.html",
	        "json:target/cucumber.json"
	    },
	    monochrome = true
	)
	public class HealthInsurance_TestRunner extends AbstractTestNGCucumberTests {

	    // Enables parallel execution
	    @Override
	    @org.testng.annotations.DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	}
