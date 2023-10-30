package com.mobile.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.mobile.integrations.MobileDriverManager.getDriver;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class MobileUtils {

    private static String sessionId;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(SessionId sessionId) {
        MobileUtils.sessionId = String.valueOf(sessionId);
    }

    public static boolean isAndroid() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(ANDROID);
    }

    public static boolean isIOS() {
        return getDriver().getCapabilities().getCapability(PLATFORM_NAME).toString().equalsIgnoreCase(IOS);
    }
}
