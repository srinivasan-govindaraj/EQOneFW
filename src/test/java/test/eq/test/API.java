package test.eq.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class API {
    Map<String, String> headers = new HashMap<>();

    void head() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + "accessToken");

        Response response = RestAssured.given()
                .headers(headers)
                .get("https://api.example.com/endpoint");

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + "accessToken")
                .build();

        response = RestAssured.given()
                .spec(requestSpec)
                .get("/endpoint");

        /**
         * Session filter is really goood
         */

        SessionFilter sessionFilter = new SessionFilter();
        RestAssured.given()
                .auth().basic("username", "password")
                .filter(sessionFilter)
                .get("https://api.example.com/login");

// Subsequent requests using the same session
        response = RestAssured.given()
                .filter(sessionFilter)
                .get("https://api.example.com/endpoint");

    }

    public static void compareResponses(String response1, String response2) throws JSONException, JSONException {
        JSONAssert.assertEquals(response1, response2, false);
        System.out.println("Responses are equal");
    }
}
