package com.apollo247.testing.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "./src/test/java/com/apollo247/testing/features",
    glue = "com.apollo247.testing.stepdefinitions",
    plugin = {
        "pretty",
        "html:Reports/account-module-report.html",
        "json:Reports/account-module-report.json"

    },
    monochrome = true
)

public class MyAccountRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}