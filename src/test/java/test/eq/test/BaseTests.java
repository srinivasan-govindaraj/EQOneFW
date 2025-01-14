package test.eq.test;

import dev.eq.factory.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.annotation.Documented;
import java.util.Map;


public class BaseTests {
    protected static Logger log = LogManager.getLogger();
protected BaseTests()
{

}
    /*@BeforeSuite
    public void beforeSuite() throws IOException {
        initReport();
    }
    @AfterSuite
    public void finish() throws IOException {
        flushReport();
    }*/
    @BeforeMethod
    protected void setUp(Object[] data)  {
       // Report.createTest(method.getName());
        Map<Object,Object> map = (Map<Object, Object>) data[0];
        Driver.initDriver(map.get("browser").toString());
    }

    @AfterMethod
    protected void tearDown(ITestResult iTestResult)
    {

        Driver.quitDriver();

    }
}
