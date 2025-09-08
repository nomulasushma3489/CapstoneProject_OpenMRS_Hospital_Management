package pages;

import java.util.Map;
import org.openqa.selenium.By;
import factory.DriverFactory;
import utils.ElementUtils;
import utils.WaitUtils;

public class AddPatientPage {
	
	ElementUtils util = new ElementUtils(DriverFactory.getDriver());
	WaitUtils waitUtil = new WaitUtils(DriverFactory.getDriver(),10);
	private By registerPatientMenu = By.id("referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension");
	private By givenName = By.name("givenName");
	private By familyName =By.name("familyName");
	private By nextButton = By.id("next-button");
	private By gender = By.id("gender-field");
	private By years = By.id("birthdateYears-field");
	private By months = By.id("birthdateMonths-field");
	private By address = By.id("address1");
	private By phoneNumber = By.name("phoneNumber");
	private By submitButton = By.id("submit");
	private By patientId =By.xpath("//em[normalize-space()='Patient ID']");
	private By errorMessage = By.xpath("//span[@class='field-error']");

	public void clickRegisterPatientMenu() {
	    waitUtil.safeClick(registerPatientMenu);  // robust click
	    waitUtil.waitForTitleContains("OpenMRS Electronic Medical Record");
	}
	
	public void enterPatientDetails(Map<String, String> data) {

		util.doSendKeys(givenName, data.get("givenname"));
		util.doSendKeys(familyName, data.get("familyname"));
		util.doClick(nextButton);
		
		util.doSelectByVisibleText(gender, data.get("gender"));
		util.doClick(nextButton);
		
		util.doSendKeys(years, data.get("years"));
		util.doSendKeys(months, data.get("months"));
		util.doClick(nextButton);
		
		util.doSendKeys(address, data.get("address"));
		util.doClick(nextButton);
		
		util.doSendKeys(phoneNumber, data.get("phonenumber"));
		util.doClick(nextButton);
		util.doClick(nextButton);		
	}
	public void clickSubmit() throws InterruptedException {
		Thread.sleep(5000);
		util.doClick(submitButton);
	}
	
	public String validateText() {
		waitUtil.waitForElementVisible(patientId);
		return util.doGetText(patientId);
	}
	
	public boolean isPatientIdVisible() {
		return waitUtil.waitForElementVisible(errorMessage).isDisplayed();
    }

    public boolean hasFieldError(String fieldLabel) {
    	return waitUtil.waitForElementVisible(errorMessage).isDisplayed();
    }
	
	public boolean isErrorDisplayed() {   	
    	return waitUtil.waitForElementVisible(errorMessage).isDisplayed();
    }
	public void enterPatientdetail(Map<String, String> data) {
		util.doSendKeys(givenName, data.get("givenname"));
		util.doSendKeys(familyName, data.get("familyname"));
		util.doClick(nextButton);
	}
	
}
