package com.mobile.interactions;

import com.mobile.integrations.MobileDriverManager;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.Locale;

public class Scroll {

    public static void scrollByDirectionInElement(WebElement element, String dir, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Distancia de scroll debe estar entre 0 y 1");
        }
        int seconds = 1;
        Dimension dimension = element.getSize();
        Point coordinates = new Point(element.getLocation().getX(), element.getLocation().getY());
        Point midPoint = new Point((int) (coordinates.x + dimension.width * 0.5), (int) (coordinates.y + dimension.height * 0.5));
        int top = midPoint.y - (int) ((dimension.height * 0.5) * distance);
        int bottom = midPoint.y + (int) ((dimension.height * 0.5) * distance);
        int left = midPoint.x - (int) ((dimension.width * 0.5) * distance);
        int right = midPoint.x + (int) ((dimension.width * 0.5) * distance);
        TouchAction action = new TouchAction((PerformsTouchActions) MobileDriverManager.getDriver());
        switch (dir.toUpperCase(Locale.ROOT)) {
            case "UP":
                action
                        .press(PointOption.point(midPoint.x, top))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                        .moveTo(PointOption.point(midPoint.x, bottom))
                        .release().perform();
                break;
            case "DOWN":
                action
                        .press(PointOption.point(midPoint.x, bottom))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                        .moveTo(PointOption.point(midPoint.x, top))
                        .release().perform();
                break;
            case "LEFT":
                action
                        .press(PointOption.point(left, midPoint.y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                        .moveTo(PointOption.point(right, midPoint.y))
                        .release().perform();
                break;
            case "RIGHT":
                action
                        .press(PointOption.point(right, midPoint.y))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                        .moveTo(PointOption.point(left, midPoint.y))
                        .release().perform();
                break;
        }
    }
}
