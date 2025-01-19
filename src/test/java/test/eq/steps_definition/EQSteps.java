package test.eq.steps_definition;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import dev.eq.dataprovider.Jsonutill;
import dev.eq.enums.Props;
import dev.eq.factory.ReportManager;
import dev.eq.utills.Utills;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import test.eq.pages.Login;
import test.eq.pojo.Test;

import static dev.eq.factory.DriverManager.getDriver;
import static dev.eq.log.BaseLogger.log;


public class EQSteps {

    @Given("the Maker has chosen a word")
    public void the_maker_has_chosen_a_word() {
        // Write code here that turns the phrase above into concrete actions
        log.info("Given Steps");
       log.info(new Test().getName());
        RestAssured.baseURI = "https://fakestoreapi.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/products");

        log.info("Response Body: " + response.getBody().asString());
        log.info("Status Code: " + response.getStatusCode());
        //ReportManager.StartTest().pass(MarkupHelper.createJsonCodeBlock(response.getBody()));
        getDriver().get(Utills.getKey(Props.URL));
        getDriver().manage().window().maximize();
        log.info(Jsonutill.get(Props.URL));
    }
    @When("the Breaker makes a guess")
    public void the_breaker_makes_a_guess() {
        // Write code here that turns the phrase above into concrete actions
       log.info("When Steps");
        System.out.println(new Test().getPassword());
    }
    @Then("the Maker is asked to score")
    public void the_maker_is_asked_to_score() {
        // Write code here that turns the phrase above into concrete actions
        log.info("When Steps");
        String title = new Login().enterUserName("Admin")
                .enterPassword("admin123")
                .clickLogin()
                .welcome().getTitle();
        Assertions.assertThat(title).isEqualTo("OrangeHRM");

    }

}
