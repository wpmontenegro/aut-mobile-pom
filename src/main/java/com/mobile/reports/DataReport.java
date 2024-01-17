package com.mobile.reports;

import io.cucumber.plugin.event.Status;
import org.openqa.selenium.remote.SessionId;

import static com.mobile.util.Constants.EMPTY;
import static com.mobile.util.Constants.LIMIT_NUMBER_CHARACTERS_REPORT;
import static com.mobile.util.GenerateData.limitCharacters;

public class DataReport {

    private static Status status;
    private static String errorMessage;
    private static String sessionId;

    public static void setSessionId(SessionId sessionId) {
        DataReport.sessionId = String.valueOf(sessionId);
    }

    public static String getSessionId() {
        return sessionId;
    }

    public static void setStatus(Status status) {
        DataReport.status = status;
    }

    public static Status getStatus() {
        return status;
    }

    public static void setErrorMessage(String errorMessage){
        DataReport.errorMessage = limitCharacters(errorMessage, LIMIT_NUMBER_CHARACTERS_REPORT);
    }

    public static String getErrorMessage(){
        return errorMessage != null ? errorMessage : EMPTY;
    }

}
