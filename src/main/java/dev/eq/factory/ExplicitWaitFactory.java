package dev.eq.factory;

import dev.eq.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static dev.eq.constants.Constants.getEXPLICITWAIT;
import static dev.eq.factory.DriverManager.getDriver;

public final class ExplicitWaitFactory {
    private ExplicitWaitFactory()
    {

    }
    public static WebElement performExplicitWait(By by, WaitStrategy waitStrategy)
    {
        WebElement element = null;
        if(waitStrategy == WaitStrategy.CLICKABLE)
        {
           element = new WebDriverWait(getDriver(), Duration.ofSeconds(getEXPLICITWAIT()))
                    .pollingEvery(Duration.ofSeconds(getEXPLICITWAIT()))
                    .ignoring(ElementClickInterceptedException.class)
                    .withTimeout(Duration.ofSeconds(getEXPLICITWAIT()))
                    .until(ExpectedConditions.elementToBeClickable(by));

        }
        else if(waitStrategy == WaitStrategy.PRESENCE)
        {
            element = new WebDriverWait(getDriver(), Duration.ofSeconds(getEXPLICITWAIT()))
                    .pollingEvery(Duration.ofSeconds(getEXPLICITWAIT()))
                    .ignoring(ElementClickInterceptedException.class)
                    .withTimeout(Duration.ofSeconds(getEXPLICITWAIT()))
                    .until(ExpectedConditions.presenceOfElementLocated(by));

        }
        else if(waitStrategy == WaitStrategy.VISIBLE)
        {
           element = new WebDriverWait(getDriver(), Duration.ofSeconds(getEXPLICITWAIT()))
                    .pollingEvery(Duration.ofSeconds(getEXPLICITWAIT()))
                    .ignoring(ElementClickInterceptedException.class)
                    .withTimeout(Duration.ofSeconds(getEXPLICITWAIT()))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));

        }
        else if (waitStrategy == WaitStrategy.NONE)
        {
            element = getDriver().findElement(by);
        }
        return element;
    }
}
