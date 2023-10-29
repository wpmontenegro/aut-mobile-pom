package com.bdd.view;

import com.mobile.integrations.MobileBase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MenuView extends MobileBase {

    @iOSXCUITFindBy(iOSNsPredicate = "label == \"1\" AND name == \"tab bar option cart\"")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]")
    private WebElement cartMenu;

    public void tapCartMenu(){
        cartMenu.click();
    }
}
