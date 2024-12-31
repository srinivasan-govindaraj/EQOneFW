package org.eq.factory;


import org.eq.utills.Utills;
import org.eq.dataprovider.Jsonutill;
import org.eq.enums.Props;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import static org.eq.factory.DriverManager.*;

public final class Driver {
    private Driver()
    {

    }

    public static void initDriver() throws Exception {
        if(Objects.isNull(getDriver()))
        {
            /* String seleniumVersion = "4.27.0";
            String command = String.format(
                    "java -jar /Users/srinivasangovindaraj/IdeaProjects/FW/EQOneFW/Selenium_Grid/selenium-server-4.27.0.jar standalone --driver-configuration display-name=\"Chrome\" max-sessions=10 stereotype=\"{\\\"browserName\\\":\\\"chrome\\\"}\"",
                    seleniumVersion

            );
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.inheritIO();
            Process process = processBuilder.start();

            // Wait for the Grid to start (you may need to adjust the time)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Selenium Grid started with Chrome and 10 max sessions");
            ChromeOptions options = new ChromeOptions();*/
            setDriver(new ChromeDriver());

            //setDriver(new RemoteWebDriver(new URL("http://localhost:4444"), options));
            getDriver().get(Utills.getKey(Props.URL));
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            System.out.println(Jsonutill.get(Props.URL));
        }

    }
    public static void quitDriver()
    {
        if(Objects.nonNull(getDriver()))
        {
            getDriver().quit();
            unload();
        }

    }

}
