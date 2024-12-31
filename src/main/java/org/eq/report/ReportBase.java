package org.eq.report;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static org.eq.report.Report.flushReport;
import static org.eq.report.Report.initReport;

public class ReportBase {
    private ReportBase()
    {

    }
    @BeforeSuite
    public void beforeSuite() throws Exception {
        initReport();
    }
    @AfterSuite
    public void finish() throws Exception {
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
