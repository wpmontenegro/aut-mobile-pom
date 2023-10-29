package com.mobile.integrations.drivers;

import com.mobile.exceptions.AutomationException;
import com.mobile.util.BrowserStackDevices;
import io.appium.java_client.remote.options.BaseOptions;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Set;

import static com.mobile.integrations.capabilities.CapabilityType.*;
import static com.mobile.integrations.capabilities.SetCapabilities.setAppiumHub;
import static com.mobile.integrations.properties.MobileProperties.*;
import static com.mobile.util.Constants.BS_SUFFIX;
import static com.mobile.util.Constants.EMPTY;

public class BrowserStackDriver {
    private static final String BROWSERSTACK_FORMAT = "browserstack.%s";
    private static final String BROWSERSTACK_HUB = "hub.browserstack.com/wd/hub";

    public static String getUrl() {
        String user = getUser();
        String keyAccess = getAccessKey();
        if (user.isEmpty() || keyAccess.isEmpty()) {
            throw new AutomationException("Te has intentado conectar a browserstack pero debes definir correctamente las credenciales");
        }
        return String.format("https://%s:%s@%s", user, keyAccess, BROWSERSTACK_HUB);
    }

    public static String getUser() {
        String userFromProperties = getPropertyValue(String.format(BROWSERSTACK_FORMAT, "user"));
        return System.getenv("BROWSERSTACK_USER") != null ? System.getenv("BROWSERSTACK_USER") : userFromProperties;
    }

    private static String getAccessKey() {
        String keyFromProperties = getPropertyValue(String.format(BROWSERSTACK_FORMAT, "key"));
        return System.getenv("BROWSERSTACK_KEY") != null ? System.getenv("BROWSERSTACK_KEY") : keyFromProperties;
    }

    private static boolean isActive() {
        return getBooleanPropertyValue(String.format(BROWSERSTACK_FORMAT, "active"));
    }

    public static void setBrowserStackDriver(BaseOptions<?> options) {
        if (isActive()) {
            setAppiumHub(getUrl());
            Set<String> propertyNames = getPropertyNames();
            for (String propertyName : propertyNames) {
                if (propertyName.startsWith(BS_SUFFIX)) {
                    options.setCapability(propertyName.replace(BS_SUFFIX, EMPTY), getPropertyValue(propertyName));
                }
            }
            options.setCapability(BUILD_NAME, System.getProperty(BUILD_NAME));
            options.setCapability(PROJECT_NAME, System.getProperty(PROJECT_NAME));
            options.setCapability(APP, System.getProperty(APP));

            if (ObjectUtils.anyNull(getPropertyValue(String.join(BS_SUFFIX, DEVICE_NAME)),
                    getPropertyValue(String.join(BS_SUFFIX, OS_VERSION)))) {
                BrowserStackDevices device = BrowserStackDevices.getRandomDevice();
                options.setCapability(DEVICE_NAME, device.getDeviceName());
                options.setCapability(OS_VERSION, device.getOsVersion());
            }
        }
    }
}
