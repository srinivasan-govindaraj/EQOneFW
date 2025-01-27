package test.eq.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.performance.Performance;
import org.openqa.selenium.devtools.v132.performance.model.Metric;
import org.testng.annotations.Test;
import test.eq.pojo.EventEntry;
import test.eq.pojo.NetworkEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SeleniumDevtools {
    private SeleniumDevtools()
    {

    }

    @Test
    public void HardevTools() throws IOException {
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));

        // Capture network events
        List<EventEntry> events = new ArrayList<>();
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            events.add(new EventEntry(responseReceived.getRequestId(), responseReceived.getResponse()));
        });

        // Navigate to a website
        driver.get("https://google.com");

        // Generate HAR JSON
        JsonObject harJson = generateHar(events);

        // Generate HTML report
        String htmlReport = generateHtmlReport(harJson);

        // Save the HTML report to a file
        Files.write(Paths.get("output.html"), htmlReport.getBytes());

        driver.quit();

    }

    private static JsonObject generateHar(List<EventEntry> events) {
        JsonObject har = new JsonObject();
        JsonObject log = new JsonObject();
        har.add("log", log);
        log.addProperty("version", "1.2");

        JsonObject creator = new JsonObject();
        creator.addProperty("name", "EQ");
        log.add("creator", creator);

        JsonArray entries = new JsonArray();
        for (EventEntry event : events) {
            try {
                JsonObject entry = new JsonObject();
                entry.addProperty("startedDateTime", new Date().toInstant().toString());

                // Add request details
                JsonObject request = new JsonObject();
                request.addProperty("url", event.getResponse().getUrl());
                entry.add("request", request);

                // Add response details
                JsonObject response = new JsonObject();
                response.addProperty("status", event.getResponse().getStatus());
                response.addProperty("statusText", event.getResponse().getStatusText());
                response.addProperty("mimeType", event.getResponse().getMimeType());

                // Handle bodySize
                long bodySize = (long) event.getResponse().getEncodedDataLength();
                response.addProperty("bodySize", bodySize);
                entry.add("response", response);

                // Add timing information
                JsonObject timings = new JsonObject();
                // Handle responseTime
                if (event.getResponse().getTiming() != null) {
                    double responseTime = (double) event.getResponse().getResponseTime().get().toJson();
                    timings.addProperty("time", responseTime);
                } else {
                    timings.addProperty("time", -1);
                }
                entry.add("timings", timings);

                entries.add(entry);
            } catch (Exception e) {
                e.printStackTrace(); // Handle exceptions
            }
        }
        log.add("entries", entries);

        return har;
    }



    private static String generateHtmlReport(JsonObject har) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>HAR Report</title></head><body>");
        html.append("<h1>HAR Report</h1>");
        html.append("<table border='1'><tr><th>Name</th><th>Status</th><th>Type</th><th>Initiator</th><th>Size</th><th>Time</th></tr>");

        JsonObject log = har.getAsJsonObject("log");
        if (log != null) {
            JsonArray entries = log.getAsJsonArray("entries");
            if (entries != null) {
                for (JsonElement entryElement : entries) {
                    JsonObject entry = entryElement.getAsJsonObject();
                    JsonObject request = entry.getAsJsonObject("request");
                    JsonObject response = entry.getAsJsonObject("response");
                    JsonObject timings = entry.getAsJsonObject("timings");

                    String url = request != null ? getStringValue(request, "url", "N/A") : "N/A";
                    int status = response != null ? getIntValue(response, "status", -1) : -1;
                    String type = response != null ? getStringValue(response, "mimeType", "N/A") : "N/A";
                    String initiator = "N/A"; // Initiator information is not available in the current implementation
                    long size = response != null ? getLongValue(response, "bodySize", -1) : -1;
                    long time = timings != null ? getLongValue(timings, "time", -1) : -1;

                    html.append("<tr>");
                    html.append("<td>").append(url).append("</td>");
                    html.append("<td>").append(status).append("</td>");
                    html.append("<td>").append(type).append("</td>");
                    html.append("<td>").append(initiator).append("</td>");
                    html.append("<td>").append(size).append("</td>");
                    html.append("<td>").append(time).append("</td>");
                    html.append("</tr>");
                }
            }
        }

        html.append("</table></body></html>");
        return html.toString();
    }

    private static String getStringValue(JsonObject obj, String key, String defaultValue) {
        JsonElement element = obj.get(key);
        return element != null && !element.isJsonNull() ? element.getAsString() : defaultValue;
    }

    private static int getIntValue(JsonObject obj, String key, int defaultValue) {
        JsonElement element = obj.get(key);
        return element != null && !element.isJsonNull() ? element.getAsInt() : defaultValue;
    }
    private static long getLongValue(JsonObject obj, String key, long defaultValue) {
        JsonElement element = obj.get(key);
        return element != null && !element.isJsonNull() ? element.getAsLong() : defaultValue;
    }

    @Test
    public void performancelog() throws IOException {
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://google.com");
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));

        List<Metric> metrics = devTools.send(Performance.getMetrics());
        Map<String, Number> performanceData = new HashMap<>();
        for (Metric metric : metrics) {
            performanceData.put(metric.getName(), metric.getValue());
        }
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Performance Metrics Report</title></head><body>");
        html.append("<h1>Performance Metrics Report</h1>");
        html.append("<table border='1'><tr><th>Metric</th><th>Value</th></tr>");

        for (Map.Entry<String, Number> entry : performanceData.entrySet()) {
            html.append("<tr>");
            html.append("<td>").append(entry.getKey()).append("</td>");
            html.append("<td>").append(entry.getValue()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");
        Files.write(Paths.get("performance_report.html"), html.toString().getBytes());
        driver.quit();




    }

    @Test
    public void NetworkLogs() throws IOException {
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        List<NetworkEvent> networkEvents = new ArrayList<>();
        devTools.addListener(Network.requestWillBeSent(), request -> {
            networkEvents.add(new NetworkEvent(
                    request.getRequest().getUrl(),
                    request.getRequest().getMethod(),
                    request.getRequest().getHeaders().toJson().toString()
            ));
        });

        driver.get("https://www.google.com");

        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Network Logs Report</title></head><body>");
        html.append("<h1>Network Logs Report</h1>");
        html.append("<table border='1'><tr><th>URL</th><th>Method</th><th>Headers</th></tr>");

        for (NetworkEvent event : networkEvents) {
            html.append("<tr>");
            html.append("<td>").append(event.getUrl()).append("</td>");
            html.append("<td>").append(event.getMethod()).append("</td>");
            html.append("<td>").append(event.getHeaders()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");
        Files.write(Paths.get("network_logs_report.html"), html.toString().getBytes());
        driver.quit();



    }



}





