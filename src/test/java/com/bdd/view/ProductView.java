package com.bdd.view;

import com.mobile.integrations.MobileBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductView extends MobileBase {

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Add To Cart\"")
    @AndroidFindBy(accessibility = "Add To Cart button")
    private MobileElement addToCartButton;

    public void tapAddToCart() {
        addToCartButton.click();
    }
}
