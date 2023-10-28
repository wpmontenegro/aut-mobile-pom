package com.bdd.view;

import com.mobile.integrations.MobileBase;

import static com.mobile.integrations.MobileDriverManager.*;

public class HomeView extends MobileBase {

    private static final String XPATH_PRODUCT_ANDROID = "//android.widget.TextView[@content-desc='store item text' and @text='%s']";
    private static final String XPATH_PRODUCT_IOS = "//XCUIElementTypeStaticText[@name='store item text' and @value='%s']";

    public void tapProduct(String productName) {
        if (isAndroid()) {
            getDriver().findElementByXPath(String.format(XPATH_PRODUCT_ANDROID, productName)).click();
        } else if (isIOS()) {
            getDriver().findElementByXPath(String.format(XPATH_PRODUCT_IOS, productName)).click();
        }
    }
}
