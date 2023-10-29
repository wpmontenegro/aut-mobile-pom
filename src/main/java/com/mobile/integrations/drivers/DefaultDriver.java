package com.mobile.integrations.drivers;

import com.mobile.integrations.properties.MobileProperties;

import static com.mobile.integrations.capabilities.SetCapabilities.setAppiumHub;
import static com.mobile.integrations.capabilities.SetCapabilities.setImplicitWaitOnSeconds;

public class DefaultDriver {

    private static final String DRIVER_SUFFIX = "mobiledriver.%s";
    private static final String IMPLICIT_WAIT_PROPERTY = "implicitWaitOnSeconds";
    private static final String HUB_PROPERTY = "hub";

    public static void setDefaultDriver(){
        setImplicitWaitOnSeconds(Long.parseLong(MobileProperties.getPropertyValue(String.format(DRIVER_SUFFIX, IMPLICIT_WAIT_PROPERTY))));
        setAppiumHub(MobileProperties.getPropertyValue(String.format(DRIVER_SUFFIX, HUB_PROPERTY)));
    }
}
