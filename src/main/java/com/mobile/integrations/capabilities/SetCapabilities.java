package com.mobile.integrations.capabilities;

import com.mobile.exceptions.AutomationException;
import com.mobile.integrations.properties.MobileProperties;
import io.appium.java_client.remote.options.BaseOptions;

import java.util.Set;

import static com.mobile.integrations.drivers.BrowserStackDriver.setBrowserStackDriver;
import static com.mobile.integrations.capabilities.CapabilityType.AUTOMATION_NAME;
import static com.mobile.integrations.drivers.DefaultDriver.setDefaultDriver;
import static com.mobile.util.Constants.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class SetCapabilities {
    private static String appiumHub;
    private static Long implicitWaitOnSeconds;

    public SetCapabilities() {
        MobileProperties.loadAllProperties();
    }

    public long getImplicitWaitOnSeconds() {
        return implicitWaitOnSeconds;
    }

    public String getAppiumHub() {
        return appiumHub;
    }

    public static void setAppiumHub(String hub) {
        appiumHub = hub;
    }

    public static void setImplicitWaitOnSeconds(Long seconds) {
        implicitWaitOnSeconds = seconds;
    }

    public BaseOptions<?> loadAppiumOptions() {
        BaseOptions<?> options = initializeBaseCapabilities();
        setPlatformSpecificCapabilities(options);
        setDefaultDriver();
        setBrowserStackDriver(options);
        return options;
    }

    private BaseOptions<?> initializeBaseCapabilities() {
        BaseOptions<?> options = new BaseOptions<>();
        Set<String> propertyNames = MobileProperties.getPropertyNames();
        for (String propertyName : propertyNames) {
            if (propertyName.startsWith(APPIUM_SUFFIX)) {
                options.setCapability(propertyName.replace(APPIUM_SUFFIX, EMPTY), MobileProperties.getPropertyValue(propertyName));
            }
        }
        return options;
    }

    private void setPlatformSpecificCapabilities(BaseOptions<?> options) {
        String platform = PLATFORM.toLowerCase();
        if (platform.equalsIgnoreCase(ANDROID)) {
            options.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
            options.setCapability(PLATFORM_NAME, ANDROID);
        } else if (platform.equalsIgnoreCase(IOS)) {
            options.setCapability(AUTOMATION_NAME, IOS_XCUI_TEST);
            options.setCapability(PLATFORM_NAME, IOS);
        } else {
            throw new AutomationException("Plataforma mobile no soportada");
        }
    }
}
