package com.mobile.integrations;

import com.mobile.exceptions.AutomationException;
import com.mobile.util.MobileProperties;
import io.appium.java_client.remote.options.BaseOptions;

import java.util.Set;

import static com.mobile.util.Constants.*;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class Capabilities {

    private static final String DRIVER_SUFFIX = "mobiledriver.%s";
    private static final String IMPLICIT_WAIT_PROPERTY = "implicitWaitOnSeconds";
    private static final String HUB_PROPERTY = "hub";
    private final MobileProperties properties;

    public Capabilities() {
        properties = new MobileProperties();
        String platform = PLATFORM.toLowerCase();
        properties.loadProperties(PROPERTIES_DEFAULT_NAME);
        if (platform.equalsIgnoreCase(ANDROID) || platform.equalsIgnoreCase(IOS)){
            properties.loadProperties(platform);
        } else {
            throw new AutomationException("Plataforma mobile inválida o no soportada");
        }
    }

    public long getImplicitWaitOnSeconds() {
        return Long.parseLong(properties.getPropertyValue(String.format(DRIVER_SUFFIX, IMPLICIT_WAIT_PROPERTY)));
    }

    public String getAppiumHub() {
        return properties.getPropertyValue(String.format(DRIVER_SUFFIX, HUB_PROPERTY));
    }

    public BaseOptions<?> getAppiumOptions() {
        BaseOptions<?> options = new BaseOptions<>();
        Set<String> propertyNames = properties.getProperty().stringPropertyNames();
        for (String propertyName : propertyNames) {
            if (propertyName.startsWith(APPIUM_SUFFIX)) {
                options.setCapability(propertyName.replace(APPIUM_SUFFIX, EMPTY), properties.getPropertyValue(propertyName));
            }
        }
        return options;
    }
}
