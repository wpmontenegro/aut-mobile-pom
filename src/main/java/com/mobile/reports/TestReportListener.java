package com.mobile.reports;

import com.mobile.integrations.drivers.BrowserStackDriver;
import com.mobile.integrations.scenario.ManageScenario;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;

import static com.mobile.util.Constants.EMPTY;

public class TestReportListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        if(ManageScenario.getScenario().isFailed()){
            DataReport.setErrorMessage(event.getResult().getError().toString());
        } else {
            DataReport.setErrorMessage(EMPTY);
        }
        DataReport.setStatus(event.getResult().getStatus());
        BrowserStackDriver.setTestResults();
    }
}
