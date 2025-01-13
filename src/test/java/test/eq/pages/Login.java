package test.eq.pages;

import test.eq.test.OrangeLogin;
import dev.eq.base.SeleniumBaseAction;
import dev.eq.enums.WaitStrategy;
import org.openqa.selenium.By;

import static dev.eq.factory.DriverManager.getDriver;

public final class Login extends SeleniumBaseAction
        {
    private final By textboxUsername = By.name("username");
    private final By textboxPassword = By.xpath("//input[@name='password' and @type='password']");
    private final By buttonLogin = By.xpath("//button[@type='submit']");



    public Login enterUserName(String uname)
    {
        enterText(textboxUsername,uname,WaitStrategy.PRESENCE);
        return this;

    }
    public  Login enterPassword(String password)
    {
        enterText(textboxPassword,password,WaitStrategy.PRESENCE);
        return this;

    }

    public  OrangeLogin clickLogin()
    {
        click(buttonLogin,WaitStrategy.CLICKABLE);
        return new OrangeLogin();
    }
    public String getTitle()
    {
        return getDriver().getTitle();
    }


}


