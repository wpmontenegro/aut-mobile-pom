package com.mobile.integrations.driver;

import com.mobile.exceptions.AutomationException;
import com.mobile.integrations.capabilities.SetCapabilities;
import com.mobile.logs.AutomationLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.URL;
import java.time.Duration;
import java.util.Locale;

import static com.mobile.util.Constants.PLATFORM;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class MobileDriverManager {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setMobileDriver() {
        SetCapabilities setCapabilities = new SetCapabilities();
        BaseOptions<?> options = setCapabilities.loadAppiumOptions();
        AutomationLogger.logInfo("Automatización corriendo en {0}", PLATFORM.toUpperCase(Locale.ROOT));

        String url = setCapabilities.getAppiumHub();
        try {
            driver = new AppiumDriver(new URL(url), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(setCapabilities.getImplicitWaitOnSeconds()));
        } catch (Exception exception) {
            throw new AutomationException("Ocurrió un error al levantar el driver con la URL del servidor de Appium", exception);
        }
        AutomationLogger.logInfo("Driver levantado con las {0}", getDriver().getCapabilities());
    }

    public static void quitDriver() {
        if (getDriver() != null)
            driver.quit();
    }

    public static boolean isAndroid() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(ANDROID);
    }

    public static boolean isIOS() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(IOS);
    }
}
