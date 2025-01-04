package test.eq.pages;

import dev.eq.base.SeleniumBaseAction;
import dev.eq.enums.WaitStrategy;
import org.openqa.selenium.By;

public class AmazonHome extends SeleniumBaseAction {

    private final By Hamburgermenu = By.id("nav-hamburger-menu");

    public AmazonHamburg clickHamburgermenu()
    {
        click(Hamburgermenu, WaitStrategy.CLICKABLE);
        return new AmazonHamburg();
    }
}
