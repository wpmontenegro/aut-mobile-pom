package com.mobile.models;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static DataManager instance = null;
    private static Map<String, String> testData = null;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            testData = new HashMap<>();
        }
        return instance;
    }

    public Map<String, String> getTestData() {
        return testData;
    }
}
