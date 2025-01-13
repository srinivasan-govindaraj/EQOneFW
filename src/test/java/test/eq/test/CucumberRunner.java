package test.eq.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/test/eq/scenarios",
        glue = "test/eq/steps_definition",
        tags = "@SmokeTest",//@smoke or @regression
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",  // ToDO; chnage the path
                "json:target/cucumber-reports/report.json"
        },
        //dryRun = true,
        monochrome = true ///  for coloring the output

)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
