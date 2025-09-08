package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import utils.AppLogger;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.ExtentReportManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import factory.DriverFactory;

public class Hooks {

	private static ExtentReports extent;
    private static ExtentTest scenarioTest;
    private static ThreadLocal<ExtentTest> scenarioNode = new ThreadLocal<>();
    private static final Logger log = AppLogger.get(LoginSteps.class);
    private static final ThreadLocal<Integer> stepNo = ThreadLocal.withInitial(() -> 0);
    private static final ThreadLocal<Integer> step = ThreadLocal.withInitial(() -> 0);
    
    ConfigReader reader = new ConfigReader();
    ExcelUtils excelUtil = new ExcelUtils();
    
	 @BeforeAll
	    public static void before_all() {
	        extent = ExtentReportManager.getInstance();
	 }
	 @Before
	 public void beforeScenario(Scenario scenario) throws IOException {
	    	DriverFactory.initDriver(reader.getProperty("browser"));
		    DriverFactory.getDriver();
	        scenarioTest = extent.createTest(scenario.getName());
	        scenarioNode.set(scenarioTest);
	        excelUtil.setExcelFile(reader.getProperty("excelPath"),reader.getProperty("sheetName"));
	        String scenario1 = scenario.getName().replaceAll("[^a-zA-Z0-9-_\\.]", "_");
	        ThreadContext.put("scenario", scenario1);  // used by log4j2.xml
	        stepNo.set(0);
	        log.info("===== START SCENARIO =====");
	 }
	 @BeforeStep
	 public void beforeStep() {
	        int n = stepNo.get() + 1;
	        stepNo.set(n);
	        ThreadContext.put("step", String.format("%02d", n)); // used by pattern
	    }


	    @AfterStep
	    public void afterStep(Scenario scenario) {
	        try {
	            step.set(step.get() + 1);
	            WebDriver driver = DriverFactory.getDriver();

	            // Get screenshot bytes once (reuse for attach + file)
	            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	            // Attach to Cucumber report
	            String attachName = "Step-" + step.get();
	            scenario.attach(bytes, "image/png", attachName);

	            // Persist to disk (test-output/screenshots/<scenario>_step_<n>_<ts>.png)
	            Path dir = Paths.get(System.getProperty("user.dir"), "test-output", "screenshots");
	            Files.createDirectories(dir);
	            String safeScenario = scenario.getName().replaceAll("[^a-zA-Z0-9-_\\.]", "_");
	            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
	            String fileName = safeScenario + "_step_" + String.format("%02d", step.get()) + "_" + timestamp + ".png";
	            Path filePath = dir.resolve(fileName);
	            FileUtils.writeByteArrayToFile(filePath.toFile(), bytes);

	            // Log in Extent
	            if (scenarioNode.get() != null) {
	                if (scenario.isFailed()) {
	                    scenarioNode.get()
	                        .log(Status.FAIL, "Step " + step.get() + " failed")
	                        .fail(MediaEntityBuilder.createScreenCaptureFromPath(filePath.toString()).build());
	                } else {
	                    scenarioNode.get()
	                        .log(Status.PASS, "Step " + step.get() + " passed")
	                        .pass(MediaEntityBuilder.createScreenCaptureFromPath(filePath.toString()).build());
	                }
	            }

	        } catch (Exception e) {
	            // Avoid failing the test because screenshot failed
	            scenario.log("Screenshot capture failed: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    @After
	    public void afterScenario() {
	        // clean up counter for the next scenario
	    	step.remove();
	        log.info("===== END SCENARIO =====");
	        ThreadContext.clearAll(); 		// prevents next scenario from reusing MDC
	        stepNo.remove();
	    }
	 @AfterAll
	    public static void after_all() {
	        extent.flush(); // write report      
	    }    

    @After
    public void tearDown() {
        System.out.println(">> Closing browser after scenario");
        DriverFactory.quitDriver();
    }
    
 // ===== helper to log into both log4j2 and Extent =====
    public static void logStep(String message) {
        log.info(message);
        if (scenarioNode.get() != null) {
            scenarioNode.get().info(message);
        }
    }
}
