package test.eq.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.Listeners;

@CucumberOptions(
        dryRun = false,
        features = {"src/test/java/test/eq/scenarios"},
        glue = {"test.eq.steps_definition","test.eq.hooks"},
       // extraGlue = {"test.eq.hooks.Hooks"}, glue and extraglue cant be used sametime
        tags = "@SmokeTest",//@smoke or @regression
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/report.html",
                "json:test-output/cucumber-reports/report.json"
        },
       // name = {"Login", "Checkout"}, to run specific scenario name
        monochrome = true,///  for coloring the output
        snippets = CucumberOptions.SnippetType.CAMELCASE

)

@Listeners(dev.eq.listners.CuCumberListners.class)
public class CucumberRunner extends AbstractTestNGCucumberTests {
        private TestNGCucumberRunner testNGCucumberRunner;
        /*@Override
        @DataProvider
        public Object[][] scenarios() {

            return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
        }*/


}
