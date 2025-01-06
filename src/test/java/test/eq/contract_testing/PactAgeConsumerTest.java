package test.eq.contract_testing;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "dateProvider", port = "8080")
public class PactAgeConsumerTest {

    @Pact(consumer = "ageConsumer")
    public V4Pact validDateFromProvider(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-type", "application/json");

        return builder
                .given("valid date received from provider")
                .uponReceiving("valid date from provider")
                .method("GET")
                .queryMatchingDate("date", "2001-02-03")
                .path("/provider/validDate")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(LambdaDsl.newJsonBody((object) -> {
                    object.numberType("year", 2000);
                    object.numberType("month", 8);
                    object.numberType("day", 3);
                    object.numberType("name", "something");
                    object.booleanType("isValidDate", true);
                }).build())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "validDateFromProvider")
    public void testValidDateFromProvider(MockServer mockServer) throws IOException {
        Response response = given()
                .queryParam("date", "2001-02-03")
                .when()
                .get(mockServer.getUrl() + "/provider/validDate")
                .then()
                .extract()
                .response();

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("isValidDate")).isEqualTo("true");


//        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/provider/validDate?date=2001-02-03")
//                .execute().returnResponse();
//
//        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
//        assertThat(JsonPath.read(httpResponse.getEntity().getContent(), "$.isValidDate").toString()).isEqualTo("true");
    }
}