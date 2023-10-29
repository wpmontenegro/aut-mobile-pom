package com.mobile.interactions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

import static com.mobile.integrations.driver.MobileDriverManager.getDriver;
import static com.mobile.integrations.driver.MobileDriverManager.isAndroid;

public class NativeKeyboard {
    private static final String XPATH_IOS = "//XCUIElementTypeKey[@name='%s']";

    public void typeNumber(String number) {
        char[] digits = number.toCharArray();
        for (char d : digits) {
            if (isAndroid()) {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + d)));
            } else {
                getDriver().findElement(By.xpath(String.format(XPATH_IOS, d))).click();
            }
        }
    }
}
