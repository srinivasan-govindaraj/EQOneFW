package test.eq.contract_testing;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import dev.eq.Main;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(classes = Main.class)
@Provider("ProductService")
@PactFolder("target/pacts")
public class ProviderPactTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
        RestAssured.baseURI = "http://localhost:8080";
    }

   /* @BeforeEach
    void setUpww(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }*/

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("given condition")
    public void setUpState() {
        // Use REST Assured to set up the provider state if needed
        RestAssured.given()
                .when()
                .post("/setup-state")
                .then()
                .statusCode(200);
    }
}
