package org.eq.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eq.base.SeleniumBaseAction;
import org.eq.dataprovider.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.*;
import java.util.List;

import static org.eq.factory.DriverManager.getDriver;
import static org.eq.report.Report.createTest;

public class ExtendReport {
    WebDriver getDriver = new ChromeDriver();

    @Test
    public void report() throws IOException, InterruptedException {
        // directory where output is to be printed
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/Report/index.html")
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
        ExtentSparkReporter failspark = new ExtentSparkReporter("test-output/Report/fail_index.html")
                .filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL,Status.WARNING}).apply();

        ExtentReports extent = new ExtentReports();
        spark.loadXMLConfig(new File("src/main/resources/ExtendConfig.xml"));
        failspark.loadXMLConfig(new File("src/main/resources/ExtendConfig.xml"));
        /*spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("eq");   ---> optional we can control on the config
        spark.config().setReportName("eq i love you ");*/
        extent.attachReporter(spark,failspark);
        String jsondata = "{\n" +
                "   \"accounting\":[\n" +
                "      {\n" +
                "         \"firstName\":\"John\",\n" +
                "         \"lastName\":\"Doe\",\n" +
                "         \"age\":23\n" +
                "      },\n" +
                "      {\n" +
                "         \"firstName\":\"Mary\",\n" +
                "         \"lastName\":\"Smith\",\n" +
                "         \"age\":32\n" +
                "      }\n" +
                "   ],\n" +
                "   \"sales\":[\n" +
                "      {\n" +
                "         \"firstName\":\"Sally\",\n" +
                "         \"lastName\":\"Green\",\n" +
                "         \"age\":27\n" +
                "      },\n" +
                "      {\n" +
                "         \"firstName\":\"Jim\",\n" +
                "         \"lastName\":\"Galley\",\n" +
                "         \"age\":41\n" +
                "      }\n" +
                "   ]\n" +
                "}";


        ExtentTest test = extent.createTest("Login Test")
                .assignAuthor("eq")
                .assignCategory("RT")
                .assignDevice("Iphone");// create test node
        getDriver.get("https://google.com");
        test.pass("login test started succesfully");// create test steps
        test.pass("Element screen shot",MediaEntityBuilder.createScreenCaptureFromBase64String(getDriver.findElement(By.name("q")).getScreenshotAs(OutputType.BASE64)).build());
        getDriver.findElement(By.name("q")).sendKeys("EQ", Keys.ENTER);
       getDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        test.pass("Screenhot with local path", MediaEntityBuilder.createScreenCaptureFromPath(takescreenShot()).build());
        test.pass("Screenhot with existing convert to base", MediaEntityBuilder.createScreenCaptureFromBase64String(takescreenShotBas64()).build());
        test.pass("Screenhot with direct 64", MediaEntityBuilder.createScreenCaptureFromBase64String(DirectBase64()).build());
        test.pass("eq you ar my everything");
        test.pass("your happiness is important for me d eq ");
        test.info("your voice is drug d eq ");
        Arrays.asList(new String[]{"EQ","you","really","good","soul"}).forEach(test::pass);
        test.pass(MarkupHelper.createOrderedList(Arrays.asList(new String[]{"EQ","you","really","good","soul"})).getMarkup());
        test.pass(MarkupHelper.createUnorderedList(Arrays.asList(new String[]{"EQ","you","really","good","soul"})).getMarkup());
        Map<String,String> map = new HashMap<>();
        map.put("Eq","you");
        map.put("true","you");
        map.forEach((k,v)-> test.pass(k+":"+v));
        test.pass(MarkupHelper.createUnorderedList(map).getMarkup());
        test.pass(MarkupHelper.createLabel("Om Muruga", ExtentColor.GREEN));
        test.info(MarkupHelper.createCodeBlock(jsondata, CodeLanguage.JSON));
       // test.warning("you are great d eq ");

        ExtentTest test1 = extent.createTest("Home Test")
                .assignAuthor("siree")
                .assignCategory("Smoke")
                .assignDevice("Android");// create test node
        test1.pass("login test started succesfully"); // create test steps
        test1.pass("eq you ar my everything");
        test1.pass("your happiness is important for me d eq ");
        test1.info("your voice is drug d eq ");
        test1.warning("you are great d eq ");
        test1.fail("failure");
        test1.fail(MarkupHelper.createLabel("Fail",ExtentColor.RED));

        extent.flush(); // unless you called this method report will ready
        Desktop.getDesktop().browse(new File("test-output/Report/index.html").toURI());
       // Desktop.getDesktop().browse(new File("test-output/Report/fail_index.html").toURI());
        getDriver.quit();


    }


    @Test(dataProvider = "getData1")
    public void testdata2(Data d )
    {
        createTest("EQ I Love You");
        System.out.println(d.getA());
        System.out.println("I love you eq ");
        System.out.println(d.getB());

    }
    @DataProvider
    public Object[][] getData1()
    {
        return new Object[][]
                {
                        {"Eq"},
                        {"is"},
                        {"My everything"}

                };
    }


    private String takescreenShot() throws IOException {
       File file = ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.FILE);
       String Path = System.getProperty("user.dir")+"/test-output/Screenshots/image.png";
       FileUtils.copyFile(file,new File(Path));
       // getDriver.quit();
       return Path;
    }
    private String takescreenShotBas64() throws IOException {
        File file = ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.FILE);
        String Path = System.getProperty("user.dir")+"/test-output/Screenshots/image.png";
        FileUtils.copyFile(file,new File(Path));
        byte[] image = IOUtils.toByteArray(new FileInputStream(Path));

        return Base64.getEncoder().encodeToString(image);
    }

    private String DirectBase64()
    {
        return ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.BASE64);
    }
}
