package com.eq.pages;

import com.eq.test.OrangeLogin;
import org.eq.base.SeleniumBaseAction;
import org.eq.enums.WaitStrategy;
import org.eq.factory.ReportManager;
import org.eq.report.ExtendLogger;
import org.eq.report.Report;
import org.openqa.selenium.By;

import static org.eq.factory.DriverManager.getDriver;

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


