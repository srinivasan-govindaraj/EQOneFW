package test.eq.steps_definition;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import dev.eq.dataprovider.Jsonutill;
import dev.eq.enums.Props;
import dev.eq.factory.ReportManager;
import dev.eq.utills.Utills;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import test.eq.pages.Login;
import test.eq.pojo.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static dev.eq.factory.DriverManager.getDriver;
import static dev.eq.log.BaseLogger.log;
import static org.testng.AssertJUnit.assertTrue;


public class EQSteps {

    @Given("the Maker has chosen a word")
    public void the_maker_has_chosen_a_word(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String, String>> map = dataTable.asMaps();
        log.info("From the feature steps-->"+map.get(0).get("url"));
        log.info("Given Steps");
       log.info(new Test().getName());
        RestAssured.baseURI = "https://fakestoreapi.com";


        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/products");

        log.info("Response Body: " + response.getBody().asString());
        log.info("Status Code: " + response.getStatusCode());
        //ReportManager.StartTest().pass(MarkupHelper.createJsonCodeBlock(response.getBody()));
        getDriver().get(Utills.getKey(Props.URL));
        /**
         * Contract Testing deque library
         */
        AxeBuilder axeBuilder = new AxeBuilder()
              /*  .withTags(Collections.singletonList("wcag21aa"))
                .disableRules(Collections.singletonList("color-contrast"));*/
                .withRules(Arrays.asList("color-contrast","image-alt"));
        try {
            Results axeResults = axeBuilder.analyze(getDriver());
            log.info(axeResults.violationFree());
            log.info(axeResults.getPasses());
            log.info(axeResults.getErrorMessage());
           // assertTrue(axeResults.violationFree());
            List<Rule> violations = axeResults.getViolations();
            for (Rule violation : violations) {
                log.info(violation.getDescription());
            }
        } catch (RuntimeException e) {
            // Do something with the error
        }


        // getDriver().manage().window().maximize();
        log.info(Jsonutill.get(Props.URL));
    }
    @When("the Breaker makes a guess")
    public void the_breaker_makes_a_guess() {
        // Write code here that turns the phrase above into concrete actions
       log.info("When Steps");
        System.out.println(new Test().getPassword());
    }
    @Then("the Maker is asked to score {string} and {string}")
    public void the_maker_is_asked_to_score(String uname,String pass) {
        // Write code here that turns the phrase above into concrete actions
        log.info("When Steps");
        String title = new Login().enterUserName(uname)
                .enterPassword(pass)
                .clickLogin()
                .welcome().getTitle();
        Assertions.assertThat(title).isEqualTo("OrangeHRM");

    }

}
