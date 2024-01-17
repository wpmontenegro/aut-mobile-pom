package com.mobile.util;

import static com.mobile.integrations.MobileDriverManager.getDriver;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class MobileUtils {

    public static boolean isAndroid() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(ANDROID);
    }

    public static boolean isIOS() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(IOS);
    }
}
