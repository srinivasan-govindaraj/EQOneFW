package dev.eq.report;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static dev.eq.report.Report.flushReport;
import static dev.eq.report.Report.initReport;

public class ReportBase {
    private ReportBase()
    {

    }
    @BeforeSuite
    public void beforeSuite()  {
        initReport();
    }
    @AfterSuite
    public void finish()  {
        flushReport();
    }

    @BeforeMethod
    public void setUp()
    {

    }

    @AfterMethod
    public void tearDown()
    {

    }

}
