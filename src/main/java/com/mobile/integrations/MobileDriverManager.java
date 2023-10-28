package com.mobile.integrations;

import com.mobile.exceptions.AutomationException;
import com.mobile.logs.AutomationLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.mobile.util.Constants.PLATFORM;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class MobileDriverManager {

    private static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public static void setMobileDriver() {
        Capabilities capabilities = new Capabilities();
        DesiredCapabilities desiredCapabilities = capabilities.getAppiumProperties();

        String platform = PLATFORM.toLowerCase();
        if (platform.equalsIgnoreCase(ANDROID)) {
            desiredCapabilities.setCapability("automationName", ANDROID_UIAUTOMATOR2);
            desiredCapabilities.setCapability("platformName", ANDROID);
        } else if (platform.equalsIgnoreCase(IOS)) {
            desiredCapabilities.setCapability("automationName", IOS_XCUI_TEST);
            desiredCapabilities.setCapability("platformName", IOS);
        } else {
            throw new AutomationException("Plataforma mobile no soportada");
        }
        AutomationLogger.logInfo("Automatización corriendo en {0}", PLATFORM.toUpperCase(Locale.ROOT));

        try {
            driver = new AndroidDriver<>(new URL(capabilities.getAppiumHub()), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(capabilities.getImplicitWaitOnSeconds(), TimeUnit.SECONDS);
        } catch (MalformedURLException malformedURLException) {
            throw new AutomationException("Ocurrió un error con la URL del servidor de Appium", malformedURLException);
        }
        AutomationLogger.logInfo("Driver levantado con las {0}", getDriver().getCapabilities());
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static boolean isAndroid() {
        return getDriver().getCapabilities().getCapability(MobileCapabilityType.PLATFORM_NAME).toString().equalsIgnoreCase(ANDROID);
    }

    public static boolean isIOS() {
        return getDriver().getCapabilities().getCapability(MobileCapabilityType.PLATFORM_NAME).toString().equalsIgnoreCase(IOS);
    }
}
