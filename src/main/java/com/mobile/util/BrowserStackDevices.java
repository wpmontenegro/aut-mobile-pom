package com.mobile.util;

import com.mobile.exceptions.AutomationException;

import java.util.Arrays;
import java.util.List;

import static com.mobile.util.Constants.PLATFORM;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public enum BrowserStackDevices {
    SAMSUNG_GALAXY_S23_ULTRA("Samsung Galaxy S23 Ultra ", "13.0", ANDROID),
    SAMSUNG_GALAXY_S22_ULTRA("Samsung Galaxy S22 Ultra ", "12.0", ANDROID),
    IPHONE_15_PRO_MAX("iPhone 15 Pro Max", "17", IOS),
    IPHONE_14_PRO_MAX("iPhone 14 Pro Max", "16", IOS);
    private final String deviceName;
    private final String osVersion;
    private final String platform;

    BrowserStackDevices(String deviceName, String osVersion, String platform){
        this.deviceName = deviceName;
        this.osVersion = osVersion;
        this.platform = platform;
    }

    public String getDeviceName(){
        return deviceName;
    }

    public String getOsVersion(){
        return osVersion;
    }

    public String getPlatform(){
        return platform;
    }

    public static BrowserStackDevices getRandomDevice(){
        String platform = PLATFORM.toLowerCase();
        List<BrowserStackDevices> devices = Arrays.stream(BrowserStackDevices.values())
                .filter(BrowserStackDevices -> BrowserStackDevices.getPlatform().equalsIgnoreCase(platform))
                .toList();
        if (devices.isEmpty()){
            throw new AutomationException("No devices available on the specified platform");
        }
        int randomIndex = GenerateData.randomInteger(0, devices.size());
        return devices.get(randomIndex);
    }
}
