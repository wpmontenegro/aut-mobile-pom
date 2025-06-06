package com.bdd.view;

import com.mobile.integrations.MobileBase;
import com.mobile.interactions.Gestures;
import com.mobile.util.Direction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.mobile.integrations.MobileDriverManager.getDriver;
import static com.mobile.util.MobileUtils.isAndroid;
import static com.mobile.util.MobileUtils.isIOS;

public class HomeView extends MobileBase {

    @iOSXCUITFindBy(accessibility = "products screen")
    @AndroidFindBy(className = "android.widget.ScrollView")
    private WebElement scrollView;

    private static final String XPATH_PRODUCT_ANDROID = "//android.widget.TextView[@content-desc='store item text' and @text='%s']";
    private static final String XPATH_PRODUCT_IOS = "//XCUIElementTypeStaticText[@name='store item text' and @value='%s']";

    public void tapProduct(String productName) {
        Gestures.scrollByDirectionInRect(scrollView, Direction.DOWN, 0.25);
        if (isAndroid()) {
            tapElement(getDriver().findElement(By.xpath(String.format(XPATH_PRODUCT_ANDROID, productName))));
        } else if (isIOS()) {
            tapElement(getDriver().findElement(By.xpath(String.format(XPATH_PRODUCT_IOS, productName))));
        }
    }
}
