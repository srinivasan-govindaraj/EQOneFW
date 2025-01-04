package test.eq.pages;

import dev.eq.base.SeleniumBaseAction;
import dev.eq.enums.WaitStrategy;
import org.openqa.selenium.By;

import static dev.eq.utills.DynamicXpath.getXpath;

public class AmazonHamburg extends SeleniumBaseAction {

    private final String link = "//ul[@class='hmenu hmenu-visible']/li//a[text()='%s']";
    public BestSellers clickHamburgLink(String menuText)
    {
        String newLink = getXpath(link,menuText);
        click(By.xpath(newLink),WaitStrategy.CLICKABLE);
        if(menuText.equalsIgnoreCase("Best Sellers"))
        {
            return new BestSellers();
        }
        return null;
    }
}
