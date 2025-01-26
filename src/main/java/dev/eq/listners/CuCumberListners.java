package dev.eq.listners;


import com.aventstack.extentreports.markuputils.MarkupHelper;
import dev.eq.factory.ReportManager;
import dev.eq.report.ExtendLogger;
import dev.eq.utills.ScenarioContext;
import dev.eq.utills.ScenarioContextManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

import static dev.eq.log.BaseLogger.log;
import static dev.eq.report.Report.flushReport;
import static dev.eq.report.Report.initReport;


public class CuCumberListners implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        // ISuiteListener.super.onStart(suite);

        log.info("Suite Started:"+suite.getName());
        // DOMConfigurator.configure("log4j2.xml");
        ScenarioContext scenarioContext = ScenarioContextManager.getContext();
        scenarioContext.putObject("EQ","EQ");
        log.info(" This one Scenario context"+scenarioContext.getObject("EQ"));
        //PropertyConfigurator.configure("src/main/resources/log4j.properties");
        initReport();


    }

    @Override
    public void onFinish(ISuite suite) {
        //ISuiteListener.super.onFinish(suite);
        log.info("Suite Ended"+suite.getName());
        flushReport();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // ITestListener.super.onTestSuccess(result);
        log.info("Testing success");
        ReportManager.StartTest().pass(result.getMethod().getMethodName() + "is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //ITestListener.super.onTestFailure(result);
        log.debug("Testing Failure");
        log.debug(result.getThrowable().getStackTrace());
        try {
            ExtendLogger.fail(result.getMethod().getMethodName() + "is failed",true);
            ReportManager.StartTest().fail(MarkupHelper.createCodeBlock(Arrays.toString(result.getThrowable().getStackTrace())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //ITestListener.super.onTestSkipped(result);
        log.warn("Testing Skipped");
        ReportManager.StartTest().skip(result.getMethod().getMethodName() + "is skipped");
    }
}
