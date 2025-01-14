package dev.eq.listners;



import com.aventstack.extentreports.markuputils.MarkupHelper;
import dev.eq.annotation.EQFrameworkAnnotation;
import dev.eq.factory.ReportManager;
import dev.eq.report.ExtendLogger;
import dev.eq.report.Report;

import org.apache.logging.log4j.LogManager;
import org.testng.*;


import java.util.Arrays;
import java.util.logging.Logger;

import static dev.eq.report.Report.flushReport;
import static dev.eq.report.Report.initReport;


public class Listners implements ISuiteListener, ITestListener {
    protected static org.apache.logging.log4j.Logger log = LogManager.getLogger();
    @Override
    public void onStart(ISuite suite) {
       // ISuiteListener.super.onStart(suite);
        Logger.getAnonymousLogger().info("Suite Started");
        //PropertyConfigurator.configure("src/main/resources/log4j.properties");
        initReport();


    }

    @Override
    public void onFinish(ISuite suite) {
        //ISuiteListener.super.onFinish(suite);
       log.info("Suite Ended");
        flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        //ITestListener.super.onTestStart(result);
        log.info("Test Started");
        Report.createTest(result.getMethod().getDescription());
       Report.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(EQFrameworkAnnotation.class).author());
       Report.addCatagory(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(EQFrameworkAnnotation.class).category());

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

    @Override
    public void onStart(ITestContext context) {
       // ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        //ITestListener.super.onFinish(context);
    }
}
