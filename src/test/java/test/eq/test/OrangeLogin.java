package test.eq.test;

import test.eq.pages.Login;
import dev.eq.base.SeleniumBaseAction;
import dev.eq.enums.WaitStrategy;
import dev.eq.factory.ReportManager;
import org.openqa.selenium.By;

public final class OrangeLogin extends SeleniumBaseAction {

    private final By linkWelcome = By.xpath("//h6[text()='Dashboard']");
    private final By buttonLogoutProfile = By.xpath("//p[@class='oxd-userdropdown-name']");
    private final By linkLogout = By.xpath("//a[text()='Logout']");

    public  Login welcome()
    {
        ReportManager.StartTest().pass("Landed to the page");
        //new WebDriverWait(getDriver(),Duration.ofSeconds(20)).until(d ->d.findElement(linkWelcome).isDisplayed());
        click(buttonLogoutProfile, WaitStrategy.CLICKABLE);
        ReportManager.StartTest().info("We are in the Home Page");
        click(linkLogout,WaitStrategy.CLICKABLE);
        ReportManager.StartTest().pass("test case completed");
        return new Login();
    }


}
