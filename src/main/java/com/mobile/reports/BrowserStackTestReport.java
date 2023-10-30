package com.mobile.reports;

import com.mobile.exceptions.AutomationException;
import com.mobile.util.GenerateData;
import com.mobile.util.MobileUtils;
import com.mobile.logs.AutomationLogger;
import io.cucumber.plugin.event.Status;
import org.junit.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static com.mobile.integrations.ManageScenario.getScenario;
import static com.mobile.integrations.drivers.BrowserStackDriver.getAccessKey;
import static com.mobile.integrations.drivers.BrowserStackDriver.getUser;
import static com.mobile.reports.TestReportListener.getErrorMessage;
import static com.mobile.reports.TestReportListener.getStatus;

public class BrowserStackTestReport {

    private static final String BS_SESSION_URL = "https://api-cloud.browserstack.com/app-automate/sessions/%s.json";

    private static URL getSessionURI() {
        URL uri = null;
        String sessionId = MobileUtils.getSessionId();
        try {
            uri = new URL(String.format(BS_SESSION_URL, sessionId));
        } catch (MalformedURLException e) {
            AutomationLogger.throwing("getSessionURI", e);
        }
        return uri;
    }

    public static void updateReport() {
        String jsonBody = "{\"status\": \"" + getCompatibleStatus(getStatus()) + "\", \"reason\": \"" + getErrorMessage() + "\", \"name\": \"" + getScenario().getName() + "\"}";
        AutomationLogger.logInfo(jsonBody);
        URL url = getSessionURI();
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            String authBase64 = GenerateData.base64(String.format("%s:%s", getUser(), getAccessKey()));
            connection.setRequestProperty("Authorization", "Basic " + authBase64);
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            Assert.assertEquals(connection.getResponseCode(), 200);
        } catch (IOException e) {
            throw new AutomationException("Ha ocurrido un error al subir el estado del test a BrowserStack", e);
        }
        connection.disconnect();
    }

    private static String getCompatibleStatus(Status status) {
        return switch (status) {
            case PASSED -> "passed";
            case FAILED, UNDEFINED -> "failed";
            default -> "completed";
        };
    }
}
