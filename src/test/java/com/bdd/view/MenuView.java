package com.bdd.view;

import com.mobile.integrations.MobileBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuView extends MobileBase {

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"1\" AND name == \"tab bar option cart\"")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]")
    private MobileElement cartMenu;

    public void tapCartMenu(){
        cartMenu.click();
    }
}
