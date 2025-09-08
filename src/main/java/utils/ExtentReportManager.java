package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
    private static ExtentReports extent;
    static ConfigReader reader = new ConfigReader();

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/ExtentReports/extent-report.html";		//path where html report is saved
            
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Test Report");
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            
            // Add system/environment details
            extent.setSystemInfo("Tester", "QA Team");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", System.getProperty("browser", reader.getProperty("browser")));
            extent.setSystemInfo("Logs (HTML)", System.getProperty("user.dir") + "/test-output/logs/automation.html");

        }
        return extent;
    }

}
