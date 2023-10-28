package com.bdd.view;

import com.mobile.integrations.MobileBase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MyCartView extends MobileBase {

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='product label']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"product label\"]")
    private WebElement productLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Remove Item\"")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"remove item\"]")
    private WebElement removeItemButton;

    public String getProductLabel(){
        return productLabel.getText();
    }

    public void tapRemoveItem(){
        removeItemButton.click();
    }
}
