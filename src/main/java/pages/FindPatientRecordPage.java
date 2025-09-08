package pages;

import java.util.Map;

import org.openqa.selenium.By;

import factory.DriverFactory;
import utils.ElementUtils;
import utils.WaitUtils;

public class FindPatientRecordPage {
	
	ElementUtils util = new ElementUtils(DriverFactory.getDriver());
	WaitUtils waitUtil = new WaitUtils(DriverFactory.getDriver(),8);
	
	private By findPatientRecordMenu = By.id("coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension");
	private By searchField = By.id("patient-search");
	private By searchDataTable = By.cssSelector("table#patient-search-results-table");
	private By searchData = By.cssSelector("table#patient-search-results-table tbody tr td");
	private By patientName = By.xpath("//ul[@id='breadcrumbs']/li[last()]");
	private By nonMatchingText = By.xpath("//td[@class='dataTables_empty']");
	
	public void clickOnMenu() {
		util.doClick(findPatientRecordMenu);
	}
	
	public void serachRecord(Map<String, String> data) throws InterruptedException {
		util.doSendKeys(searchField, data.get("patientrecord"));
		waitUtil.waitForElementVisible(searchDataTable);
		Thread.sleep(5000);
		waitUtil.waitForElementClickable(searchData);
		util.doClick(searchData);
		Thread.sleep(5000);
	}

	public String getPatientRecord() {
		return util.doGetText(patientName);
	}
	
	public String getNonMatchingText() {
		return util.doGetText(nonMatchingText);
	}
	

}

