package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Lightweight Log4j2 helper.
 * - AppLogger.get(MyClass.class) to get a logger
 * - AppLogger.setScenario("Scenario Name") to tag logs (optional)
 * - AppLogger.clearContext() after scenario (optional)
 */
public final class AppLogger {

    private AppLogger() {
    	
    }

    public static Logger get(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /** Optional: call at @Before to tag logs with scenario/test name */
    public static void setScenario(String scenarioName) {
        if (scenarioName != null) {
            // Keep filename-safe value if you ever route per-scenario
            String safe = scenarioName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            ThreadContext.put("scenario", safe);
        }
    }

    /** Optional: call at @After to clean up */
    public static void clearContext() {
        ThreadContext.clearAll();
    }
 
}
