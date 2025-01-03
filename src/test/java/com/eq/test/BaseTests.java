package com.eq.test;

import org.eq.factory.Driver;
import org.eq.report.Report;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.eq.report.Report.flushReport;
import static org.eq.report.Report.initReport;


public class BaseTests {
protected BaseTests()
{

}
    /*@BeforeSuite
    public void beforeSuite() throws IOException {
        initReport();
    }
    @AfterSuite
    public void finish() throws IOException {
        flushReport();
    }*/
    @BeforeMethod
    protected void setUp(Object[] data)  {
       // Report.createTest(method.getName());
        Map<Object,Object> map = (Map<Object, Object>) data[0];
        Driver.initDriver(map.get("browser").toString());
    }

    @AfterMethod
    protected void tearDown(ITestResult iTestResult)
    {

        Driver.quitDriver();

    }
}
