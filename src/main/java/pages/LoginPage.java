package pages;
import org.openqa.selenium.By;
import factory.DriverFactory;
import utils.ElementUtils;
import utils.WaitUtils;

public class LoginPage {
	
	ElementUtils util = new ElementUtils(DriverFactory.getDriver());
	WaitUtils waitUtil = new WaitUtils(DriverFactory.getDriver(),10);
	
	private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");   
    private By errorMessage = By.id("error-message");
    
    public void enterUserName(String username) {
    	util.doSendKeys(usernameField, username);
	}
	public void enterPassword(String password) {
		util.doSendKeys(passwordField, password);
	}
	
	public void selectSession(String session) {
		By sessionField = By.xpath("//ul[@id='sessionLocation']//li[normalize-space()='" + session + "']");
		util.doClick(sessionField);		
	}
	public void clickLogin() {
		util.doClick(loginButton);
	}
	
	public void login(String username,String password,String language) {
		enterUserName(username);
		enterPassword(password);
		selectSession(language);
	}
	
    public boolean isErrorDisplayed() {   	
    	return waitUtil.waitForElementVisible(errorMessage).isDisplayed();
    }
    
    public String getErrorMessage() {
    	return util.doGetText(errorMessage);
    }   
}
