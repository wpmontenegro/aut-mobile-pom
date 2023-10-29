package com.mobile.integrations.driver;

import com.mobile.exceptions.AutomationException;
import com.mobile.util.MobileProperties;
import io.appium.java_client.remote.options.BaseOptions;

import java.util.Set;

import static com.mobile.integrations.driver.Capabilities.setAppiumHub;
import static com.mobile.util.Constants.BS_SUFFIX;
import static com.mobile.util.Constants.EMPTY;
import static com.mobile.util.MobileProperties.getPropertyNames;

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
        String userFromProperties = MobileProperties.getPropertyValue(String.format(BROWSERSTACK_FORMAT, "user"));
        return System.getenv("BROWSERSTACK_USER") != null ? System.getenv("BROWSERSTACK_USER") : userFromProperties;
    }

    private static String getAccessKey() {
        String keyFromProperties = MobileProperties.getPropertyValue(String.format(BROWSERSTACK_FORMAT, "key"));
        return System.getenv("BROWSERSTACK_KEY") != null ? System.getenv("BROWSERSTACK_KEY"): keyFromProperties;
    }

    public static boolean isActiveBrowserStack() {
        return MobileProperties.getPropertyValue(String.format(BROWSERSTACK_FORMAT, "active")).equalsIgnoreCase("true");
    }

    public static void setBrowserStackDriver(BaseOptions<?> options) {
        setAppiumHub(getUrl());
        Set<String> propertyNames = getPropertyNames();
        for (String propertyName : propertyNames) {
            if (propertyName.startsWith(BS_SUFFIX)) {
                options.setCapability(propertyName.replace(BS_SUFFIX, EMPTY), MobileProperties.getPropertyValue(propertyName));
            }
        }
    }
}
