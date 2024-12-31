package org.eq.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.eq.constants.Constants;
import org.eq.factory.ReportManager;

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

    public static void initReport() throws Exception {
        if(Objects.isNull(extent)) {
            spark = new ExtentSparkReporter(ExtentReortPath)
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY}).apply();
            extent = new ExtentReports();
            extent.attachReporter(spark);
            spark.loadXMLConfig(new File("src/main/resources/ExtendConfig.xml"));
        }
    }

    public static void createTest(String Case)
    {
        ReportManager.setExtTest(extent.createTest(Case));
    }

    public static void flushReport() throws Exception {
        if(Objects.nonNull(extent)) {
            extent.flush();
            //will implement the scenario context future
            Desktop.getDesktop().browse(new File(ExtentReortPath).toURI());
        }

    }
}
