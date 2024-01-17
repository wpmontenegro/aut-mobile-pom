package com.mobile.integrations.properties;

import com.mobile.exceptions.AutomationException;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import static com.mobile.util.Constants.PLATFORM;
import static com.mobile.util.Constants.PROPERTIES_DEFAULT_NAME;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class MobileProperties {

    private static final Properties properties = new Properties();

    public static void loadAllProperties(){
        MobileProperties.loadPropertiesFromFile(PROPERTIES_DEFAULT_NAME);

        String platform = PLATFORM.toLowerCase();
        if (platform.equalsIgnoreCase(ANDROID) || platform.equalsIgnoreCase(IOS)){
            MobileProperties.loadPropertiesFromFile(platform);
        } else {
            throw new AutomationException("Invalid or unsupported mobile platform");
        }
    }

    private static void loadPropertiesFromFile(String propertiesFileName) {
        try (InputStream inputStream = MobileProperties.class.getClassLoader().getResourceAsStream(String.format("%s.properties", propertiesFileName))) {
            if (inputStream == null) {
                throw new AutomationException("Properties path not found");
            }
            properties.load(inputStream);
        } catch (Exception e) {
            throw new AutomationException("Error loading properties: " + propertiesFileName, e);
        }
    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key, null);
    }

    public static boolean getBooleanPropertyValue(String key) {
        return Boolean.parseBoolean((properties.getProperty(key, Boolean.toString(false))));
    }

    public static Set<String> getPropertyNames() {
        checkPropertiesLoad();
        return properties.stringPropertyNames();
    }

    private static void checkPropertiesLoad() {
        if (properties.isEmpty()) {
            throw new AutomationException("The properties have not been loaded. Call loadProperties() first");
        }
    }
}
