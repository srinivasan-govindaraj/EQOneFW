package test.eq.contract_testing;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ProductService")
public class ConsumerPactTest {

    @Pact(consumer = "ProductCatalog", provider = "ProductService")
    public V4Pact createPact(PactDslWithProvider builder) {
        return builder
                .given("a product with ID 1 exists")
                .uponReceiving("a request for product 1")
                .path("/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"id\":1,\"name\":\"Product A\",\"price\":10.99}")
                .toPact(V4Pact.class);
    }

    @Test
    void testProductFetch(MockServer mockServer) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(mockServer.getUrl()+"/products/1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertEquals("{\"id\":1,\"name\":\"Product A\",\"price\":10.99}", response.body());
    }
}
