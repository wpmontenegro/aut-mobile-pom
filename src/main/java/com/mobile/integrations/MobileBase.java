package com.mobile.integrations;

import com.mobile.exceptions.AutomationException;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileBase {

    public MobileBase() {
        PageFactory.initElements(new AppiumFieldDecorator(MobileDriverManager.getDriver()), this);
    }

    public WebElement waitForElementVisibility(WebElement element, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(MobileDriverManager.getDriver(), Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException exception) {
            throw new AutomationException("No se puede interactuar con el elemento: " + element.toString(), exception);
        }
    }

    public void tapElement(WebElement element) {
        waitForElementVisibility(element, 0).click();
    }

    public void enterText(WebElement element, String value) {
        waitForElementVisibility(element, 0).sendKeys(value);
    }

    public String getText(WebElement element) {
        return waitForElementVisibility(element, 0).getText();
    }

    public void tapElement(WebElement element, int seconds) {
        waitForElementVisibility(element, seconds).click();
    }

    public void enterText(WebElement element, String value, int seconds) {
        waitForElementVisibility(element, seconds).sendKeys(value);
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isDisplayed(WebElement element, int seconds) {
        try {
            return waitForElementVisibility(element, seconds).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}