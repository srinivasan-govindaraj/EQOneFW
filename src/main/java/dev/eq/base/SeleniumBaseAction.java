package dev.eq.base;

import dev.eq.factory.ExplicitWaitFactory;
import dev.eq.enums.WaitStrategy;
import dev.eq.report.ExtendLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.logging.Logger;

import static dev.eq.constants.Constants.getEXPLICITWAIT;
import static dev.eq.factory.DriverManager.getDriver;

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
       highlightElement(by);
        ExplicitWaitFactory.performExplicitWait(by,waitStrategy).click();
        //ExtendLogger.pass(by.toString()+"clicked");

            ExtendLogger.pass(by.toString()+"clicked",true);
       // removeHighlight(by);


    }
    protected void enterText(By by,String value,WaitStrategy waitStrategy )
    {
        ExplicitWaitFactory.performExplicitWait(by,waitStrategy).sendKeys(value);
       highlightElement(by);
            ExtendLogger.pass("Value entered in element of: "+by.toString()+"="+value,true);
       removeHighlight(by);
        // ExtendLogger.pass("Value entered in element of: "+by.toString()+"="+value);//less verbocity
       // ReportManager.StartTest().pass("UserName" + textboxUsername); // similar to extend

    }

    protected void highlightElement(By by)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", getDriver().findElement(by));

    }

    protected void removeHighlight(By by)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute('style', '');", getDriver().findElement(by));
    }


}
