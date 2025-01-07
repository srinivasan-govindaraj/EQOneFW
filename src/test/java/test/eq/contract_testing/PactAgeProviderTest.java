package test.eq.contract_testing;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.loader.VersionSelector;
import dev.eq.Main;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(classes = Main.class)
@Provider("dateProvider")
@Consumer("ageConsumer")
@PactFolder("target/pacts")
/*@PactBroker(
        host = "localhost",port = "9292",
        consumerVersionSelectors = {
                @VersionSelector(tag = "dev"), @VersionSelector(tag = "master"), @VersionSelector(tag = "test")
        })*/
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PactAgeProviderTest {
    @BeforeEach
    void setUp(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 8080, "/"));
    }
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @LocalServerPort
    private int port;



    @BeforeAll
    static void enablePublishingPact() {
        System.setProperty("pact.verifier.publishResults", "true");
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }



    @State("valid date received from provider")
    public void validDateProvider() {

    }
}
