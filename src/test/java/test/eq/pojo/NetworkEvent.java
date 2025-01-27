package test.eq.pojo;

import org.openqa.selenium.devtools.v132.network.model.Response;

public class NetworkEvent {
    private final String url;
    private final String method;
    private final String requestHeaders;
    private String responseHeaders;
    private int statusCode;
    private long responseTime;

    public NetworkEvent(String url, String method, String requestHeaders) {
        this.url = url;
        this.method = method;
        this.requestHeaders = requestHeaders;
    }

    public void setResponse(Response response, long responseTime) {
        this.responseHeaders = response.getHeaders().toJson().toString();
        this.statusCode = response.getStatus();
        this.responseTime = responseTime;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
    public String getHeaders() {
        return "Request Headers: " + requestHeaders + "\nResponse Headers: " + responseHeaders;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return "NetworkEvent{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", statusCode=" + statusCode +
                ", responseTime=" + responseTime + "ms" +
                '}';
    }
}
