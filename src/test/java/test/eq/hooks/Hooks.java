package test.eq.hooks;

import dev.eq.factory.Driver;
import dev.eq.factory.ReportManager;
import dev.eq.report.Report;
import io.cucumber.java.*;


import static dev.eq.log.BaseLogger.log;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario)
    {
        Report.createTest(scenario.getName());
        log.info("Before Scenario"+scenario.getName());
     Driver.initDriver("chromegrid");  //chromegrid //android //ios

    }
    @After
    public void afterScenario(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            ReportManager.StartTest().fail(scenario.getName()+"-->is Failed");
        }
        else {
            ReportManager.StartTest().pass(scenario.getName()+"-->is Passed");
        }
        log.info("After Scenario"+scenario.getName());
        Driver.quitDriver();


    }

    @BeforeStep
    public void beforeStep()
    {
       log.info("before step");
    }
    @AfterStep
    public void afterStep()
    {
    log.info("after step");
    }


}
