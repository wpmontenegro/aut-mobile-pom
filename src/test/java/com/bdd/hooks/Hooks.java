package com.bdd.hooks;

import com.mobile.integrations.ManageScenario;
import com.mobile.integrations.driver.MobileDriverManager;
import com.mobile.util.ScreenShoot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        ManageScenario.setScenario(scenario);
    }

    @After(order = 0)
    public void afterScenario() {
        MobileDriverManager.quitDriver();
    }

    @After(order = 1)
    public void tearDown() {
        ScreenShoot.shotWhenFail();
    }

}
