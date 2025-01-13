package test.eq.pages;

import dev.eq.base.SeleniumBaseAction;

import static dev.eq.factory.DriverManager.getDriver;

public class BestSellers extends SeleniumBaseAction {

    public String getBestSellersPageTitle() {
return getDriver().getTitle();
    }
}
