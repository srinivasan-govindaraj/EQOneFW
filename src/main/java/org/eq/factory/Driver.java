package org.eq.factory;


import org.eq.utills.Utills;
import org.eq.dataprovider.Jsonutill;
import org.eq.enums.Props;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import static org.eq.factory.DriverManager.*;

public final class Driver {
    private Driver()
    {

    }

    public static void initDriver(String browser) throws Exception {

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
        if(Objects.isNull(getDriver()))
        {
            switch (browser)
            {
                case "chrome":
                    setDriver(new ChromeDriver());
                    break;
                case "safari":
                    setDriver(new SafariDriver());
                    break;
                case "chromegrid":
                    ChromeOptions options = new ChromeOptions();
                    options.setCapability("browserVersion", "latest");
                    options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                        /* How to add test badge */
                        put("name", "EQOneFW");

                        /* How to set session timeout */
                        put("sessionTimeout", "15m");
                        put("enableVNC",true);

                        /* How to set timezone */
                        put("env", new ArrayList<String>() {{
                            add("TZ=UTC");
                        }});

                        /* How to add "trash" button */
                        put("labels", new HashMap<String, Object>() {{
                            put("manual", "true");
                        }});

                        /* How to enable video recording */
                        put("enableVideo", true);
                    }});
                    setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    break;
            }



            //setDriver(new RemoteWebDriver(new URL("http://localhost:4444"), options));

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
