package org.eq.listners;


import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.eq.factory.Driver;
import org.eq.factory.ReportManager;
import org.eq.report.ExtendLogger;
import org.eq.report.Report;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

import static org.eq.report.Report.flushReport;
import static org.eq.report.Report.initReport;


public class Listners implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
       // ISuiteListener.super.onStart(suite);
        System.out.println("Suite Started");
        try {
            initReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        //ISuiteListener.super.onFinish(suite);
        System.out.println("Suite Ended");
        try {
            flushReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        //ITestListener.super.onTestStart(result);
        System.out.println("Testing started");
        Report.createTest(result.getMethod().getDescription());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
       // ITestListener.super.onTestSuccess(result);
        System.out.println("Testing success");
        ReportManager.StartTest().pass(result.getMethod().getMethodName() + "is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //ITestListener.super.onTestFailure(result);
        System.out.println("Testing Failure");
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
        System.out.println("Testing Skipped");
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
