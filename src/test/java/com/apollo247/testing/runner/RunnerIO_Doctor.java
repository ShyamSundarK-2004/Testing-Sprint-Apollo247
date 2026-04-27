package com.apollo247.testing.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "./src/test/java/com/apollo247/testing/features/FindDocterModule.feature",
    glue = "com.apollo247.testing.stepdefinitions",
    dryRun = false,
    tags="@Finddoctor",
    plugin = {
    		"pretty",
            "html:Reports/TestReport.html",
            "com.apollo247.testing.utilities.ExtentCucumberListener"
    },
    monochrome = true
)

public class RunnerIO_Doctor extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
	    return super.scenarios();
	}

}
