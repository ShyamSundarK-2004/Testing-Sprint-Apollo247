package com.apollo247.testing.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    // point to the features directory so all feature files (all scenarios) are picked up
    features = "src/test/java/com/apollo247/testing/features",
    glue = "com.apollo247.testing.stepdefinitions",
    dryRun = false,
    plugin = {
        "pretty",
        "html:Reports/BuyMedicine.html",
        "json:Reports/BuyMedicine-module-report.json",
        "html:Reports/BuyMedicine_CucumberReport.html",
        "com.apollo247.testing.utilities.ExtentCucumberListener"
    },
    monochrome = true
)
public class BuyMedicineRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}