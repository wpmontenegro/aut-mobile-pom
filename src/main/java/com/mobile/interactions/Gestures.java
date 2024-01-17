package com.mobile.interactions;

import com.mobile.util.Direction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static com.mobile.integrations.MobileDriverManager.getDriver;

public class Gestures {
    private static void performScroll(int startX, int startY, int endX, int endY, int speed) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                .addAction(finger.createPointerMove(Duration.ofSeconds(speed), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        getDriver().perform(Collections.singletonList(sequence));
    }

    public static void scrollByDirectionInRect(WebElement scrollRect, Direction direction, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        int speed = 1;
        Dimension dimension = scrollRect.getSize();
        Point coordinates = new Point(scrollRect.getLocation().getX(), scrollRect.getLocation().getY());
        Point midPoint = new Point((int) (coordinates.x + dimension.width * 0.5), (int) (coordinates.y + dimension.height * 0.5));
        int top = midPoint.y - (int) ((dimension.height * 0.5) * distance);
        int bottom = midPoint.y + (int) ((dimension.height * 0.5) * distance);
        int left = midPoint.x - (int) ((dimension.width * 0.5) * distance);
        int right = midPoint.x + (int) ((dimension.width * 0.5) * distance);
        switch (direction) {
            case UP -> performScroll(midPoint.x, top, midPoint.x, bottom, speed);
            case DOWN -> performScroll(midPoint.x, bottom, midPoint.x, top, speed);
            case LEFT -> performScroll(left, midPoint.y, right, midPoint.y, speed);
            case RIGHT -> performScroll(right, midPoint.y, left, midPoint.y, speed);
        }
    }
}
