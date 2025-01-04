package test.eq.test;


import dev.eq.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;


import static dev.eq.factory.DriverManager.getDriver;
public class HomePageTests  extends BaseTests{
    @Test
    public void eq1()
    {
        getDriver().findElement(By.name("q")).sendKeys(Constants.getEq1(), Keys.ENTER);
        System.out.println("Case 2");
    }

    @Test
    public void eq2()
    {
        getDriver().findElement(By.name("q")).sendKeys(Constants.getEq1(), Keys.ENTER);
        System.out.println("Case 2.2");
    }


}
