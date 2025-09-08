package stepdefinitions;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.AppLogger;
import utils.ExcelUtils;
import org.apache.logging.log4j.Logger;
public class LoginSteps {
	
	WebDriver driver = DriverFactory.getDriver();
	private static final Logger log = AppLogger.get(LoginSteps.class);
	LoginPage loginPage = new LoginPage();
	DashBoardPage dashboardPage = new DashBoardPage();
	ExcelUtils excelUtil = new ExcelUtils();
	
	@Given("the user is on the OpenMRS login page")
	public void the_user_is_on_the_open_mrs_login_page() {
		driver.get(excelUtil.getCellData(0,1));
		Hooks.logStep("Navigating to Login page");
	}

	@When("the user enters username {string} password {string} selects session {string}")
	public void the_user_enters_username_password_selects_session(String username, String password, String session) {
	    loginPage.login(username, password, session);
	    Hooks.logStep("Entering login details");
	}
	@And("clicks on Login button")
	public void clicks_on_login_button() {
		loginPage.clickLogin();
		Hooks.logStep("Clicking on login button");
	}

	@Then("the user should be directed to {string} page")
	public void the_user_should_be_directed_to_page(String title) {
		if(!dashboardPage.getTitle().contains(title)) {
			throw new AssertionError("Login failed! Expected dashboard");
		}
		Hooks.logStep("Navigating to home page");
	}

	@And("an error message {string} should be displayed")
	public void an_error_message_should_be_displayed(String expectedMessage) {
	    assertTrue("Error not displayed",loginPage.isErrorDisplayed());
	    assertEquals(expectedMessage,loginPage.getErrorMessage().trim());
	    Hooks.logStep("An error message is displayed");
	}
}
