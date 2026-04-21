package com.apollo247.testing.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

//@CucumberOptions(features = {"./src/test/java/com/apollo247/testing/features/FindDocter.feature"}, glue = "com.apollo247.testing.stepdefinitions", dryRun = true)

//@CucumberOptions(
//	    features = {"./src/test/java/com/apollo247/testing/features/hearttool.feature"},
//	    glue = "com.apollo247.testing.stepdefinitions",
//	    dryRun=false)
//@CucumberOptions(features= {"./src/test/java/com/apollo247/testing/features/location.feature"},glue="com.apollo247.testing.stepdefinitions", dryRun = false)
//@CucumberOptions(features = {"./src/test/java/com/apollo247/testing/features/MyAppointment.feature"}, glue = "com.apollo247.testing.stepdefinitions", dryRun = false)
//@CucumberOptions(features = {"./src/test/java/com/apollo247/testing/features/FilterDocter.feature"}, glue = "com.apollo247.testing.stepdefinitions", dryRun = false)
//@CucumberOptions(features = "./src/test/java/com/apollo247/testing/features/FindDocter.feature", glue = "com.apollo247.testing.stepdefinitions", dryRun = false)



@CucumberOptions(
    features = "./src/test/java/com/apollo247/testing/features/FindDocterModule.feature",
    glue = "com.apollo247.testing.stepdefinitions",
    dryRun = false,
    plugin = {
        "pretty",
        "html:Reports/TestReport.html"
    },
    tags="@filter"
    
)

public class RunnerIO_Docter extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
	    return super.scenarios();
	}

}
