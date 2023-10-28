package com.mobile.util;

import org.apache.commons.lang3.StringUtils;

public class Constants {
    public static String PROPERTIES_DEFAULT_NAME = "application";
    public static String APPIUM_SUFFIX = "appium.";
    public static String EMPTY = "";
    public static String PLATFORM = StringUtils.defaultString(System.getProperty("platform"), "android");
    public static final String RUTA_DATA_CSV = "data/%s";
}
