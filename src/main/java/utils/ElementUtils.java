package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtils {

    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void doClick(By locator) {
        driver.findElement(locator).click();
    }

    public void doSendKeys(By locator, String text) {
    	if (text == null || text.isBlank()) return;   // skip in negative tests
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public String doGetText(By locator) {
        return driver.findElement(locator).getText();
    }

    public boolean doIsDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }
    
    public void doSelectByVisibleText(By locator, String visibleText) {
    	 if (visibleText == null || visibleText.isBlank()) return; // <— important
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
  
    }
    public String getTitle() {
    	return driver.getTitle();
    }
    
 // hover over element
    public void doMoveToElement(By locator) {    	
    	Actions actions = new Actions(driver);
	    actions.moveToElement(getElement(locator)).perform();
    }

//     hover and click
    public void doMoveToElementAndClick(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locator)).click().perform();
    }
    

    

}
