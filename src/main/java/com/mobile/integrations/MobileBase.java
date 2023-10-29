package com.mobile.integrations;

import com.mobile.integrations.driver.MobileDriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MobileBase {

    public MobileBase(){
        PageFactory.initElements(new AppiumFieldDecorator(MobileDriverManager.getDriver()), this);
    }
}
