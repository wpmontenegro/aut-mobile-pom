package com.mobile.integrations.capabilities;

import java.time.Duration;

public class AppiumConfiguration {

    private static String appiumHubUrl;
    private static Duration implicitWaitDuration;

    public static String getAppiumHub() {
        return appiumHubUrl;
    }

    public static void setAppiumHub(String hubUrl) {
        appiumHubUrl = hubUrl;
    }

    public static Duration getImplicitWaitDuration() {
        return implicitWaitDuration;
    }

    public static void setImplicitWaitDuration(Long seconds) {
        implicitWaitDuration = Duration.ofSeconds(seconds);
    }
}
