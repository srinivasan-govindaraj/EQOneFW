package test.eq.pojo;
import org.openqa.selenium.devtools.v132.network.model.RequestId;
import org.openqa.selenium.devtools.v132.network.model.Response;
public class EventEntry {
    private String requestId;
    private Response response;

    public EventEntry(RequestId requestId, Response response) {
        this.requestId = String.valueOf(requestId);
        this.response = response;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "EventEntry{" +
                "requestId='" + requestId + '\'' +
                ", response=" + response +
                '}';
    }
}
