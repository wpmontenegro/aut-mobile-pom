package com.mobile.integrations;

import io.cucumber.java.Scenario;

public class ManageScenario {

    private static Scenario scenario;

    public static Scenario getScenario(){
        return scenario;
    }

    public static void setScenario(Scenario scenario){
        ManageScenario.scenario = scenario;
    }
}
