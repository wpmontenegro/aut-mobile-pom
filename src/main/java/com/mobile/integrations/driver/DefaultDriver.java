package com.mobile.integrations.driver;

import com.mobile.util.MobileProperties;

import static com.mobile.integrations.driver.Capabilities.setAppiumHub;
import static com.mobile.integrations.driver.Capabilities.setImplicitWaitOnSeconds;

public class DefaultDriver {

    private static final String DRIVER_SUFFIX = "mobiledriver.%s";
    private static final String IMPLICIT_WAIT_PROPERTY = "implicitWaitOnSeconds";
    private static final String HUB_PROPERTY = "hub";

    public static void setDefaultDriver(){
        setImplicitWaitOnSeconds(Long.parseLong(MobileProperties.getPropertyValue(String.format(DRIVER_SUFFIX, IMPLICIT_WAIT_PROPERTY))));
        setAppiumHub(MobileProperties.getPropertyValue(String.format(DRIVER_SUFFIX, HUB_PROPERTY)));
    }
}
