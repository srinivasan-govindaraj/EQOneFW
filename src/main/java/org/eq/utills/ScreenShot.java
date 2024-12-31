package org.eq.utills;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.eq.factory.DriverManager.getDriver;

public final class ScreenShot {
    private ScreenShot()
    {

    }
    public static  String getBase64()
    {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
