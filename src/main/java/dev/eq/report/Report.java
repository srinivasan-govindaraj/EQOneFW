package dev.eq.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import dev.eq.constants.Constants;
import dev.eq.enums.Category;
import dev.eq.factory.ReportManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class Report {
    private Report()
    {

    }
    private static ExtentSparkReporter spark;
    private static ExtentReports extent;
    private static String ExtentReortPath;

    static {
        try {
            ExtentReortPath = Constants.getExtentreport();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void initReport()  {
        if(Objects.isNull(extent)) {
            spark = new ExtentSparkReporter(ExtentReortPath)
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY,ViewName.AUTHOR,ViewName.EXCEPTION,ViewName.LOG}).apply();
            extent = new ExtentReports();
            extent.attachReporter(spark);
            try {
                spark.loadXMLConfig(new File("src/main/resources/ExtendConfig.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createTest(String Case)
    {
        ReportManager.setExtTest(extent.createTest(Case));
    }

    public static void flushReport()  {
        if(Objects.nonNull(extent)) {
            extent.flush();
            //will implement the scenario context future
            ReportManager.flushTest();
            try {
                Desktop.getDesktop().browse(new File(ExtentReortPath).toURI());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void addAuthors(String[] authors)
    {
        for(String auth:authors)
        {
           ReportManager.StartTest().assignAuthor(auth);
        }
    }
    public static void addCatagory(Category[] catagory)
    {
for(Category cat:catagory)
{
    ReportManager.StartTest().assignCategory(cat.toString());
}
    }

}
