package stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FindPatientRecordPage;
import pages.LoginPage;
import utils.AppLogger;
import utils.ExcelUtils;

public class FindPatientRecordSteps {
	
	WebDriver driver = DriverFactory.getDriver();
	private static final Logger log = AppLogger.get(FindPatientRecordSteps.class);
	LoginPage loginPage = new LoginPage();
	FindPatientRecordPage findPatientRecord = new FindPatientRecordPage();
	ExcelUtils excelUtil = new ExcelUtils();	
	Map<String,String> data;
	
	
	@Given("the user is logged into OpenMRS to find a patient record")
	public void the_user_is_logged_into_open_mrs_to_find_a_patient_record() throws IOException {
		driver.get(excelUtil.getCellData(0, 1));
	    loginPage.login("admin", "Admin123", "Registration Desk");
	    loginPage.clickLogin();
	    Hooks.logStep("Logged in successfully and navigated to home page");
	}
	
	@When("the user clicks on Find Patient Record")
	public void the_user_clicks_on_find_patient_record() throws InterruptedException {
		findPatientRecord.clickOnMenu();
		Hooks.logStep("user clicks on find patient record");
	}
	
	@When("the user enters patient name and click on details")
	public void the_user_enters_patient_name_and_click_on_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
	    data = dataTable.asMap(String.class,String.class);
	    findPatientRecord.serachRecord(data);
	    Hooks.logStep("user enters patient name and clicks on details");
	}
	
	@Then("the user should see a record with patient name")
	public void the_user_should_see_a_record_with_patient_name() {
		Assert.assertTrue(findPatientRecord.getPatientRecord().contains(data.get("patientrecord")));
		Hooks.logStep("user should see a record with given patient name");
	}
	
	@Then("the user should see {string}")
	public void the_user_should_see(String text) {
		findPatientRecord.getNonMatchingText();
		Assert.assertTrue(findPatientRecord.getNonMatchingText().contains(text));
		Hooks.logStep("user should see non matching text");
	}
}
