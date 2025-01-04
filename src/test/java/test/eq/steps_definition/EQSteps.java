package test.eq.steps_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import test.eq.pojo.Test;

import java.util.logging.Logger;

public class EQSteps {

    @Given("the Maker has chosen a word")
    public void the_maker_has_chosen_a_word() {
        // Write code here that turns the phrase above into concrete actions
        Logger.getAnonymousLogger().info("Given Steps");
        System.out.println(new Test().getName());
    }
    @When("the Breaker makes a guess")
    public void the_breaker_makes_a_guess() {
        // Write code here that turns the phrase above into concrete actions
        Logger.getAnonymousLogger().info("When Steps");
        System.out.println(new Test().getPassword());
    }
    @Then("the Maker is asked to score")
    public void the_maker_is_asked_to_score() {
        // Write code here that turns the phrase above into concrete actions
        Logger.getAnonymousLogger().info("When Steps");

    }

}
