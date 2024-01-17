package com.mobile.util;

import java.util.Objects;

public class Constants {
    public static String PROPERTIES_DEFAULT_NAME = "application";
    public static String APPIUM_SUFFIX = "appium.";
    public static final String BS_SUFFIX = "bs.";
    public static String EMPTY = "";
    public static String PLATFORM = Objects.toString(System.getProperty("platform"), "android");
    public static final String RUTA_DATA_CSV = "data/%s";
    public static int LIMIT_NUMBER_CHARACTERS_REPORT = 256;
}
