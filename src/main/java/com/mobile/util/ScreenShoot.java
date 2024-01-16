package com.mobile.util;

import com.mobile.integrations.MobileDriverManager;
import org.openqa.selenium.OutputType;

import static com.mobile.integrations.scenario.ManageScenario.getScenario;

public class ScreenShoot {

    public static void takeScreenShoot() {
        if (getScenario() == null)
            throw new IllegalArgumentException("No hay escenario configurado");
        if (MobileDriverManager.getDriver() == null)
            throw new IllegalArgumentException("No hay driver levantado");
        byte[] screenshot = MobileDriverManager.getDriver().getScreenshotAs(OutputType.BYTES);
        getScenario().attach(screenshot, "image/jpeg", "evidencia");
    }

    public static void shotWhenFail() {
        if (getScenario().isFailed())
            takeScreenShoot();
    }
}
