package com.mobile.util;

import com.mobile.logs.AutomationLogger;

public class Wait {

    public static void forSeconds(int timeOnSeconds) {
        try {
            Thread.sleep(timeOnSeconds * 1000L);
        } catch (InterruptedException e) {
            AutomationLogger.error("forSeconds", e);
        }
    }
}
