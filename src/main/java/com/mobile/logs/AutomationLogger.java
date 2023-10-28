package com.mobile.logs;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutomationLogger {

    public static Logger LOGGER = Logger.getLogger(AutomationLogger.class.getName());

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logInfo(String message, Object param) {
        LOGGER.log(Level.INFO, message, param);
    }

    public static void logWarning(String message) {
        LOGGER.warning(message);
    }

    public static void throwing(String method, Throwable thrown) {
        LOGGER.throwing(AutomationLogger.class.getName(), method, thrown);
    }
}