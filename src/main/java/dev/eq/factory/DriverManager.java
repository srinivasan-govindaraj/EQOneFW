package dev.eq.factory;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager()
    {

    }
    //ignore
   /* private static ThreadLocal<WebDriver> dr = ThreadLocal.withInitial(() -> {
        return new ChromeDriver();
    });*/
    //ignore
    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();
    public static WebDriver getDriver()
    {

        return dr.get();
    }
    public static void setDriver(WebDriver driver)
    {
        dr.set(driver);
    }
    public static void unload()
    {

        dr.remove();
    }
}
