package com.apollo247.testing.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "./src/test/java/com/apollo247/testing/features",
    glue = "com.apollo247.testing.stepdefinitions",
    dryRun = true,   
    plugin = {
        "pretty",
        "html:target/account-module-report.html",
        "json:target/account-module-report.json"

    }
)

public class MyAccountRunner extends AbstractTestNGCucumberTests {

}