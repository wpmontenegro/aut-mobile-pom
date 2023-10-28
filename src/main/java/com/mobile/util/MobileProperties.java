package com.mobile.util;

import com.mobile.exceptions.AutomationException;
import java.io.InputStream;
import java.util.Properties;

public class MobileProperties {

    private final Properties properties = new Properties();

    public void loadProperties(String propertiesFileName){
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(String.format("%s.properties", propertiesFileName))){
            if (inputStream == null){
                throw new AutomationException("No se encontró la ruta de las properties");
            }
            properties.load(inputStream);
        } catch (Exception e){
            throw new AutomationException(e.getMessage(), e);
        }
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperty(){
        return properties;
    }
}
