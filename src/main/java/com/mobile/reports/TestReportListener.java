package com.mobile.reports;

import com.mobile.integrations.ManageScenario;
import com.mobile.integrations.drivers.BrowserStackDriver;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;

import static com.mobile.util.Constants.EMPTY;
import static com.mobile.util.GenerateData.limitCharacters;

public class TestReportListener implements ConcurrentEventListener {
    private static Status status;
    private static String errorMessage;

    private static void setStatus(Status status) {
        TestReportListener.status = status;
    }

    public static Status getStatus() {
        return status;
    }

    private void setErrorMessage(String errorMessage){
        TestReportListener.errorMessage = limitCharacters(errorMessage, 256);
    }

    public static String getErrorMessage(){
        if (errorMessage != null)
            return errorMessage;
        return EMPTY;
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        setErrorMessage(EMPTY);
        if(ManageScenario.getScenario().isFailed()){
            setErrorMessage(event.getResult().getError().toString());
        }
        setStatus(event.getResult().getStatus());
        BrowserStackDriver.setTestResults();
    }
}
