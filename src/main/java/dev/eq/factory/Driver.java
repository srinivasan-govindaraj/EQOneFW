package dev.eq.factory;


import com.aventstack.extentreports.markuputils.MarkupHelper;
import dev.eq.exception.FWException;
import dev.eq.report.ExtendLogger;
import javafx.scene.effect.Reflection;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import static dev.eq.factory.DriverManager.*;

public final class Driver {
    /**
     * <p>Driver class is sigle done class</p>
     *
     * @author EQ
     * @version 1.0
     * @Since 140999
     * @param -browser-name to be passed
     * @see DriverManager
     * {@link ExtendLogger}
     */
    private Driver()
    {

    }

    public static void initDriver(String browser) {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            EdgeOptions edgeOptions = new EdgeOptions();
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
            ChromeOptions chromeOptions = new ChromeOptions();*/
            if (Objects.isNull(getDriver())) {
                switch (browser) {
                    case "chrome":
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--headless");
                        setDriver(new ChromeDriver(chromeOptions));
                        break;
                    case "edge":
                        edgeOptions.addArguments("--no-sandbox");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                        setDriver(new EdgeDriver(edgeOptions));
                    case "safari":
                        setDriver(new SafariDriver());
                        break;
                    case "chromegrid":

                        // chromeOptions.setCapability("browserVersion", "latest");
                        chromeOptions.setCapability("selenoid:chromeOptions", new HashMap<String, Object>() {{
                            /* How to add test badge */
                            put("name", "EQOneFW");

                            /* How to set session timeout */
                            put("sessionTimeout", "15m");
                            put("enableVNC", true);

                            /* How to set timezone */
                            put("env", new ArrayList<String>() {{
                                add("TZ=UTC");
                            }});

                            /* How to add "trash" button */
                            put("labels", new HashMap<String, Object>() {{
                                put("manual", "true");
                            }});

                            /* How to enable video recording */
                            put("enableVideo", false);
                        }});
                        try {
                            setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                        break;
                    case "edgegrid":
                        edgeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                            /* How to add test badge */
                            put("name", "EQOneFW");

                            /* How to set session timeout */
                            put("sessionTimeout", "15m");
                            put("enableVNC", true);

                            /* How to set timezone */
                            put("env", new ArrayList<String>() {{
                                add("TZ=UTC");
                            }});

                            /* How to add "trash" button */
                            put("labels", new HashMap<String, Object>() {{
                                put("manual", "true");
                            }});

                            /* How to enable video recording */
                            put("enableVideo", false);
                        }});
                        try {
                            setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                        break;
                }


                //setDriver(new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions));

            }
        }
        catch (SessionNotCreatedException e)
        {
           // ExtendLogger.fail(Method.class.getName() + "is failed",true);
            //ReportManager.StartTest().fail(MarkupHelper.createCodeBlock(Arrays.toString(e.getStackTrace())));
            e.printStackTrace();
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
