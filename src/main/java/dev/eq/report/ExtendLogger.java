package dev.eq.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import dev.eq.enums.Props;

import static dev.eq.utills.ScreenShot.getBase64;

import dev.eq.factory.ReportManager;
import dev.eq.utills.Utills;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class ExtendLogger {
    private ExtendLogger()
    {

    }
    private static Consumer<String> PASS = (message) -> ReportManager.StartTest().pass(message);
    private static Consumer<String> FAIL = (message) -> ReportManager.StartTest().fail(message);
    private static Consumer<String> SKIP = (message) -> ReportManager.StartTest().skip(message);
    private static Consumer<String> INFO = (message) -> ReportManager.StartTest().info(message);

    private static Map<String,Consumer<String>> logMap = new HashMap<>();

    static {
        logMap.put("PASS",PASS);
        logMap.put("FAIL",FAIL);
        logMap.put("SKIP",SKIP);
        logMap.put("INFO",INFO);

    }

    public static void pass(String message)
    {
        logMap.get("PASS").accept(message);
        //ReportManager.StartTest().pass(message);
    }
    public static void fail(String message)
    {
        logMap.get("FAIL").accept(message);
        //ReportManager.StartTest().fail(message);
    }
    public static void skip(String message)
    {
        logMap.get("SKIP").accept(message);
        //ReportManager.StartTest().skip(message);
    }
    public static void info(String message)
    {
        logMap.get("INFO").accept(message);
        //ReportManager.StartTest().info(message);
    }
    public static void pass(String message,boolean isScreenshotNeeded)  {
        if(Utills.getKey(Props.PASSONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else {
            pass(message);
        }

    }

    public static void fail(String message,boolean isScreenshotNeeded) {
        if(Utills.getKey(Props.FAILEDONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else {
            fail(message);
        }

    }
    public static void info(String message,boolean isScreenshotNeeded)  {
        if(Utills.getKey(Props.FAILEDONLYSS).equalsIgnoreCase("yes") && isScreenshotNeeded)
        {
            ReportManager.StartTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
        }
        else
        {
            info(message);
        }

    }






}
