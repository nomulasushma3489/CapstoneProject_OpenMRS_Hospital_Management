package stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddServiceTypePage;
import pages.LoginPage;
import utils.AppLogger;
import utils.ExcelUtils;

public class AddServiceTypeSteps {

	WebDriver driver = DriverFactory.getDriver();
	private static final Logger log = AppLogger.get(AddServiceTypeSteps.class);
	LoginPage loginPage = new LoginPage();
	AddServiceTypePage addServiceType = new AddServiceTypePage();
	ExcelUtils excelUtil = new ExcelUtils();
	
	@Given("the user is logged into OpenMRS to add service type")
	public void the_user_is_logged_into_open_mrs_to_add_service_type() throws InterruptedException, IOException {
		driver.get(excelUtil.getCellData(0, 1));
	    loginPage.login("admin", "Admin123", "Registration Desk");
	    loginPage.clickLogin();
	    Hooks.logStep("Logged in successfully and navigated to home page");
	}
	
	@When("the user clicks on Appointment Scheduling")
	public void the_user_clicks_on_appointment_scheduling() {
		addServiceType.clickAppointmentScheduling();
		Hooks.logStep("Clicked on appointment scheduling");
	}
	
	@When("the user clicks on Manage Service Types")
	public void the_user_clicks_on_manage_service_types() {
		addServiceType.clickManageServiceType();
		Hooks.logStep("Clicked on Manager Service Type");
	}
	
	@And("clicks on new service type")
	public void clicks_on_new_service_type() {
		addServiceType.clickAddServiceType();
		Hooks.logStep("Clicked on Add Service Type");
	}
	
	@When("enters details:")
	public void enters_details(io.cucumber.datatable.DataTable dataTable) {
		Map<String, String> data = dataTable.asMap(String.class,String.class);
		addServiceType.enterDetails(data);
		Hooks.logStep("user entered details");
	}
	
	@And("clicks on save button")
	public void clicks_on_save_button() {
		addServiceType.save();
		Hooks.logStep("Clicked on save button");
	}
	@Then("the user should be directed to {string}")
	public void the_user_should_be_directed_to(String text) {
		Assert.assertTrue(addServiceType.getPageText().contains(text));
		Hooks.logStep("user will be directed to "+text);
	}
	
	@Then("user should see a message {string}")
	public void user_should_see_a_message(String message) {
		Assert.assertTrue(addServiceType.getMessage().contains(message));
		Hooks.logStep("user will see a message"+message);
	}
	
}
