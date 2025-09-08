package stepdefinitions;

import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddPatientPage;
import pages.LoginPage;
import utils.AppLogger;
import utils.ExcelUtils;

public class AddPatientSteps {
	
	WebDriver driver = DriverFactory.getDriver();
//	private static final Logger log = AppLogger.get(AddPatientSteps.class);
	LoginPage loginPage = new LoginPage();
	AddPatientPage patientPage = new AddPatientPage();
	ExcelUtils excelUtil = new ExcelUtils();

	
	
	@Given("the user is logged into OpenMRS to register a patient")
	public void the_user_is_logged_into_open_mrs_to_register_a_patient() throws InterruptedException, IOException {
		driver.get(excelUtil.getCellData(0, 1));
	    loginPage.login("admin", "Admin123", "Registration Desk");
	    loginPage.clickLogin();
	    Hooks.logStep("Logged in successfully and navigated to home page");
	    
	}

	@When("the user clicks on Register a Patient")
	public void the_user_clicks_on_register_a_patient() throws InterruptedException {
	    patientPage.clickRegisterPatientMenu();
	    Hooks.logStep("Clicked on register patient menu");
	}
	
	@When("the user enters new patient details:")
	public void the_user_enters_new_patient_details(io.cucumber.datatable.DataTable dataTable) {
	    Map<String, String> data = dataTable.asMap(String.class,String.class);
	    patientPage.enterPatientDetails(data);
	    Hooks.logStep("user enters patient details");
	}
	
	@And("the user clicks on submit button")
	public void the_user_clicks_on_submit_button() throws InterruptedException {
		patientPage.clickSubmit();
		Hooks.logStep("Clicked on submit button");
	}
	
	@Then("the user should see {string} text")
	public void the_user_should_see_text(String text) {
		if(!patientPage.validateText().contains(text)) {
			throw new AssertionError("Patient ID not created");
		}
		Hooks.logStep("The user will see the "+text);
	}

    @And("I should see a validation error for {string}")
    public void i_should_see_a_validation_error_for(String fieldLabel) {
        assertTrue("Expected validation error for: " + fieldLabel,patientPage.hasFieldError(fieldLabel));
        Hooks.logStep("user will see a validation error "+fieldLabel);
    }
    
    @When("the user enters few patient details:")
	public void the_user_enters_few_patient_details(io.cucumber.datatable.DataTable dataTable) {
	    Map<String, String> data = dataTable.asMap(String.class,String.class);
	    patientPage.enterPatientdetail(data);
	    Hooks.logStep("user enters few patient details");
	}
	
		
}
