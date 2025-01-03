package org.eq.base;

import org.eq.factory.ExplicitWaitFactory;
import org.eq.enums.WaitStrategy;
import org.eq.factory.ReportManager;
import org.eq.report.ExtendLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.logging.Logger;

import static org.eq.constants.Constants.getEXPLICITWAIT;
import static org.eq.factory.DriverManager.getDriver;

public class SeleniumBaseAction {

    private boolean isDisplayed(By by)

    {
        if(Boolean.TRUE.equals(new WebDriverWait(getDriver(), Duration.ofSeconds(getEXPLICITWAIT())).until(d -> d.findElement(by).isEnabled())))
        {
            Logger.getAnonymousLogger().info("Element is Enabled" + by);
            return true;


        }
        else
        {
            Logger.getAnonymousLogger().warning("Element is not displayed" + by);
            return false;
        }


    }

    protected void click(By by , WaitStrategy waitStrategy)
    {
        ExplicitWaitFactory.performExplicitWait(by,waitStrategy).click();
        //ExtendLogger.pass(by.toString()+"clicked");

            ExtendLogger.pass(by.toString()+"clicked",true);


    }
    protected void enterText(By by,String value,WaitStrategy waitStrategy )
    {
        ExplicitWaitFactory.performExplicitWait(by,waitStrategy).sendKeys(value);
            ExtendLogger.pass("Value entered in element of: "+by.toString()+"="+value,true);
        // ExtendLogger.pass("Value entered in element of: "+by.toString()+"="+value);//less verbocity
       // ReportManager.StartTest().pass("UserName" + textboxUsername); // similar to extend

    }



}
