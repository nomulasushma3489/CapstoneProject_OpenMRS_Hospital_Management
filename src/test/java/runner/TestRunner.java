package runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
features = "src\\test\\resource\\features", 
glue = {"stepdefinitions"},
plugin = {
		"pretty", 
		"html:target/cucumber-reports/Cucumber.html", 
		"json:target/cucumber-reports/Cucumber.json", 
		"junit:target/cucumber-reports/Cucumber.xml", 
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
},
dryRun = false,
monochrome = true 
)

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
	return super.scenarios();
	}
}


