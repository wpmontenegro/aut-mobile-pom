package com.mobile.integrations;

import com.mobile.exceptions.AutomationException;
import com.mobile.integrations.capabilities.Capabilities;
import com.mobile.logs.AutomationLogger;
import com.mobile.reports.DataReport;
import io.appium.java_client.AppiumDriver;

import java.net.URL;
import java.util.Locale;

import static com.mobile.integrations.capabilities.AppiumConfiguration.getAppiumHub;
import static com.mobile.integrations.capabilities.AppiumConfiguration.getImplicitWaitDuration;
import static com.mobile.integrations.capabilities.Capabilities.getCapabilities;
import static com.mobile.util.Constants.PLATFORM;

public class MobileDriverManager {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setMobileDriver() {
        Capabilities.loadAppiumOptions();
        AutomationLogger.logInfo("Automation running on {0}", PLATFORM.toUpperCase(Locale.ROOT));
        try {
            driver = new AppiumDriver(new URL(getAppiumHub()), getCapabilities());
            driver.manage().timeouts().implicitlyWait(getImplicitWaitDuration());
            DataReport.setSessionId(driver.getSessionId());
        } catch (Exception exception) {
            throw new AutomationException("An error occurred while lifting the driver with the Appium server URL", exception);
        }
        AutomationLogger.logInfo("Driver running with the capabilities {0}", getDriver().getCapabilities());
    }

    public static void quitDriver() {
        if (getDriver() != null)
            driver.quit();
    }
}
