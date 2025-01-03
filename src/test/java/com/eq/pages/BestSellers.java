package com.eq.pages;

import org.eq.base.SeleniumBaseAction;

import static org.eq.factory.DriverManager.getDriver;

public class BestSellers extends SeleniumBaseAction {

    public String getBestSellersPageTitle() {
return getDriver().getTitle();
    }
}
