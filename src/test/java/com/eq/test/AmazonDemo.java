package com.eq.test;

import com.eq.pages.AmazonHamburg;
import com.eq.pages.AmazonHome;
import org.assertj.core.api.Assertions;
import org.eq.annotation.EQFrameworkAnnotation;
import org.eq.enums.Category;
import org.eq.report.ExtendLogger;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

import static org.eq.factory.DriverManager.getDriver;

public final class AmazonDemo extends BaseTests{

    private AmazonDemo()
    {

    }

    @EQFrameworkAnnotation(author = {"EQ","JaiSriram"},category ={Category.SMOKE,Category.REGRESSION})
    @Test
    public void testAmazon(Map<Object,Object> map)
    {
        getDriver().get("https://www.amazon.in/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        getDriver().manage().window().maximize();
        String title =  new AmazonHome().clickHamburgermenu()
                .clickHamburgLink(map.get("menutext").toString())
                .getBestSellersPageTitle();
        ExtendLogger.info("Title of the Page is :"+title);
        Assertions.assertThat(title).isNotNull().isNotBlank();
    }

}
