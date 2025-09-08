package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForTitleContains(String title) {
        return wait.until(ExpectedConditions.titleContains(title));
    }
    
    public void sleepSeconds(int seconds) {
        try { Thread.sleep(seconds * 1000L); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    public void safeClick(By locator) {
        pageReady();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            // last resort when a transient overlay intercepts the native click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        } catch (StaleElementReferenceException e) {
            // re-find and click once more if DOM changed
            el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
        }
    }

    private void pageReady() {
        wait.until(d -> "complete".equals(
            ((JavascriptExecutor)d).executeScript("return document.readyState")));
        // ignore if not present
        try { wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ui-widget-overlay"))); }
        catch (Exception ignore) {}
    }
    public void waitForUrlContains(String url) {
    	wait.until(ExpectedConditions.urlContains(url)); // landed

    }
}
