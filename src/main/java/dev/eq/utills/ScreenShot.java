package dev.eq.utills;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static dev.eq.factory.DriverManager.getDriver;

public final class ScreenShot {
    private ScreenShot()
    {

    }
    public static  String getBase64()
    {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
