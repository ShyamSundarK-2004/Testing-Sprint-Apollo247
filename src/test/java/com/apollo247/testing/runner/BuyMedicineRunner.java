package com.apollo247.testing.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/com/apollo247/testing/features/BuyMedicineFeature.feature",
    glue = "com.apollo247.testing.stepdefinitions",
    plugin = {
        "pretty",
        "html:Reports/BuyMedicine.html",
        "json:Reports/BuyMedicine-module-report.json"
    },
    tags="@SearchMedicine",
    monochrome = true
)
public class BuyMedicineRunner extends AbstractTestNGCucumberTests {

}

