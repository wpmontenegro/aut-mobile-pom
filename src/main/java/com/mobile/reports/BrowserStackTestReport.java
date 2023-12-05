package com.mobile.reports;

import com.mobile.exceptions.AutomationException;
import com.mobile.logs.AutomationLogger;
import com.mobile.util.GenerateData;
import com.mobile.util.MobileUtils;
import io.cucumber.plugin.event.Status;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.mobile.integrations.ManageScenario.getScenario;
import static com.mobile.integrations.drivers.BrowserStackDriver.getAccessKey;
import static com.mobile.integrations.drivers.BrowserStackDriver.getUser;
import static com.mobile.reports.TestReportListener.getErrorMessage;
import static com.mobile.reports.TestReportListener.getStatus;

public class BrowserStackTestReport {

    private static final String BS_SESSION_URL = "https://api-cloud.browserstack.com/app-automate/sessions/%s.json";

    private static URI getSessionURI() {
        URI uri = null;
        String sessionId = MobileUtils.getSessionId();
        try {
            uri = new URI(String.format(BS_SESSION_URL, sessionId));
        } catch (URISyntaxException e) {
            AutomationLogger.throwing("getSessionURI", e);
        }
        return uri;
    }

    public static void updateReport() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String requestBody = String.format("{\"status\": \"%s\", \"reason\": \"%s\", \"name\": \"%s\"}",
                getCompatibleStatus(getStatus()), getErrorMessage(), getScenario().getName());
        String authBase64 = GenerateData.base64(String.format("%s:%s", getUser(), getAccessKey()));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(getSessionURI())
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + authBase64)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new AutomationException("Ha ocurrido un error al subir el estado del test a BrowserStack", e);
        }
    }

    private static String getCompatibleStatus(Status status) {
        return switch (status) {
            case PASSED -> "passed";
            case FAILED, UNDEFINED -> "failed";
            default -> "completed";
        };
    }
}
