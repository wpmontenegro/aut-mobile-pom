package com.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber-report/cucumber.json", "html:target/cucumber-report/HTML/cucumber.html"},
        glue = {"com.bdd.hooks", "com.bdd.glue"},
        features = "src/test/resources",
        stepNotifications = true,
        tags = "@ADD_TO_CART"
)
public class RunnerTest {
}
