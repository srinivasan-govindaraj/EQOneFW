package dev.eq.utills;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.events.CdpEventTypes;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.Request;
import org.openqa.selenium.devtools.v138.network.model.Response;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CDPNetworkLogger {

    // POJO to hold request/response info
    public static class NetworkLogEntry {
        public String name;           // Resource name
        public String type;           // Resource type (Document, Script, etc.)
        public int status;            // HTTP Status code
        public String requestUrl;     // Full Request URL
        public String requestMethod;  // GET/POST/PUT/etc.
        public Integer responseStatusCode; // Final status code

        public NetworkLogEntry(String name, String type, int status, String requestUrl, String requestMethod, Integer responseStatusCode) {
            this.name = name;
            this.type = type;
            this.status = status;
            this.requestUrl = requestUrl;
            this.requestMethod = requestMethod;
            this.responseStatusCode = responseStatusCode;
        }

        @Override
        public String toString() {
            return String.format("[name=%s, type=%s, status=%d, Request URL=%s, Method=%s, ResponseCode=%s]",
                    name, type, status, requestUrl, requestMethod, responseStatusCode);
        }
    }

    public static void main(String[] args) {
        ChromeDriver driver = null;
        DevTools devTools = null;

        // Thread-safe, allows storing multiple entries per name if needed.
        Map<String, List<NetworkLogEntry>> networkLogs = new ConcurrentHashMap<>();

        try {
            driver = new ChromeDriver();
            devTools = driver.getDevTools();
            devTools.createSession();

            // Enable Network domain
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty()));

            // Store requestId -> request info to correlate with responses
            Map<String, Request> ongoingRequests = new ConcurrentHashMap<>();

            // Listen for requestWillBeSent event
            devTools.addListener(Network.requestWillBeSent(), request -> {
                try {
                    String requestId = request.getRequestId().toString();
                    ongoingRequests.put(requestId, request.getRequest());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Listen for responseReceived event
            devTools.addListener(Network.responseReceived(), response -> {
                try {
                    String requestId = response.getRequestId().toString();
                    Request requestObj = ongoingRequests.get(requestId);

                    if (requestObj != null) {
                        Response responseObj = response.getResponse();
                        String name = extractName(requestObj.getUrl());
                        String type = responseObj.getMimeType();
                        String method = requestObj.getMethod();

                        NetworkLogEntry entry = new NetworkLogEntry(
                                name,
                                type,
                                responseObj.getStatus(),
                                requestObj.getUrl(),
                                method,
                                responseObj.getStatus()
                        );

                        // Add to map by name
                        networkLogs.computeIfAbsent(name, k -> new ArrayList<>()).add(entry);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Example navigation (replace with your target page)
            driver.get("https://test.io/");
            Thread.sleep(5000); // Wait for network calls to complete (adjust as needed)

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        // Display results
        System.out.println("===== Network Logs =====");
        for (Map.Entry<String, List<NetworkLogEntry>> entry : networkLogs.entrySet()) {
            System.out.println("Name: " + entry.getKey());
            for (NetworkLogEntry log : entry.getValue()) {
                System.out.println(" -> " + log);
            }
        }
    }

    // Utility: extract filename/resource name
    private static String extractName(String url) {
        try {
            return url.substring(url.lastIndexOf('/') + 1).split("\\?")[0];
        } catch (Exception e) {
            return url;
        }
    }
}
