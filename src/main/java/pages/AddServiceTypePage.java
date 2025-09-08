package pages;

import java.util.Map;

import org.openqa.selenium.By;

import factory.DriverFactory;
import utils.ElementUtils;

public class AddServiceTypePage {

	ElementUtils util = new ElementUtils(DriverFactory.getDriver());
	
	private By appointmentschedulingMenu = By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension");
	private By manageServiceTypeMenu = By.id("appointmentschedulingui-manageAppointmentTypes-app");
	private By addNewServiceTypeButton = By.xpath("//button[normalize-space()='New service type']");
	private By nameField = By.id("name-field");
	private By durationField = By.id("duration-field");
	private By saveButton = By.id("save-button");
	private By manageServiceTypeText = By.id("manageAppointmentsTypeTitle");
	private By message = By.xpath("//span[@class='field-error']");	
	
	public void clickAppointmentScheduling() {
		util.doClick(appointmentschedulingMenu);
	}
	public void clickManageServiceType() {
		util.doClick(manageServiceTypeMenu);
	}
	public void clickAddServiceType() {
		util.doClick(addNewServiceTypeButton);
	}
	public void enterDetails(Map<String,String> data) {
		util.doSendKeys(nameField, data.get("name"));
		util.doSendKeys(durationField, data.get("duration"));		
	}
	
	public void save() {
		util.doClick(saveButton);
	}
	
	public String getPageText() {
		return util.doGetText(manageServiceTypeText);
	}
	public String getMessage() {
		return util.doGetText(message);
	}
}
