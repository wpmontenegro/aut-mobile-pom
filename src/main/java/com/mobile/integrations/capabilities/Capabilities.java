package com.mobile.integrations.capabilities;

import com.mobile.exceptions.AutomationException;
import com.mobile.integrations.properties.MobileProperties;
import io.appium.java_client.remote.options.BaseOptions;

import java.util.Set;

import static com.mobile.integrations.capabilities.CapabilityType.AUTOMATION_NAME;
import static com.mobile.integrations.drivers.BrowserStackDriver.setBrowserStackDriver;
import static com.mobile.integrations.drivers.DefaultDriver.setDefaultDriver;
import static com.mobile.util.Constants.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class Capabilities {

    private static final BaseOptions<?> options = new BaseOptions<>();

    public static BaseOptions<?> getCapabilities() {
        return options;
    }

    public static void loadAppiumOptions() {
        MobileProperties.loadAllProperties();
        setBaseCapabilities();
        setPlatformCapabilities();
        setDefaultDriver();
        setBrowserStackDriver(options);
    }

    private static void setBaseCapabilities() {
        Set<String> propertyNames = MobileProperties.getPropertyNames();
        for (String propertyName : propertyNames) {
            if (propertyName.startsWith(APPIUM_SUFFIX)) {
                options.setCapability(propertyName.replace(APPIUM_SUFFIX, EMPTY), MobileProperties.getPropertyValue(propertyName));
            }
        }
    }

    private static void setPlatformCapabilities() {
        String platform = PLATFORM.toLowerCase();
        if (platform.equalsIgnoreCase(ANDROID)) {
            options.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
            options.setCapability(PLATFORM_NAME, ANDROID);
        } else if (platform.equalsIgnoreCase(IOS)) {
            options.setCapability(AUTOMATION_NAME, IOS_XCUI_TEST);
            options.setCapability(PLATFORM_NAME, IOS);
        } else {
            throw new AutomationException("Mobile platform not supported: " + platform);
        }
    }
}
