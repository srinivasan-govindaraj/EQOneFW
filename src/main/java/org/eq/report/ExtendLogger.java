package org.eq.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.eq.enums.Props;
import static org.eq.factory.DriverManager.getDriver;
import static org.eq.utills.ScreenShot.getBase64;

import org.eq.factory.ReportManager;
import org.eq.utills.Utills;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ExtendLogger {
    private ExtendLogger()
    {

    }
    public static void pass(String message)
    {
        ReportManager.StartTest().pass(message);
    }
    public static void fail(String message)
    {
        ReportManager.StartTest().fail(message);
    }
    public static void skip(String message)
    {
        ReportManager.StartTest().skip(message);
    }
    public static void info(String message)
    {
        ReportManager.StartTest().info(message);
    }
    public static void pass(String message,boolean isScreenshotNeeded) throws Exception {
        if(Utills.getKey(Props.PASSONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else {
            pass(message);
        }

    }

    public static void fail(String message,boolean isScreenshotNeeded) throws Exception {
        if(Utills.getKey(Props.FAILEDONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else {
            fail(message);
        }

    }
    public static void info(String message,boolean isScreenshotNeeded) throws Exception {
        if(Utills.getKey(Props.FAILEDONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else
        {
            info(message);
        }

    }






}
