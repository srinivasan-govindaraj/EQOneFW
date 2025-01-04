package test.eq.test;

import test.eq.pages.AmazonHome;
import org.assertj.core.api.Assertions;
import dev.eq.annotation.EQFrameworkAnnotation;
import dev.eq.enums.Category;
import dev.eq.report.ExtendLogger;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

import static dev.eq.factory.DriverManager.getDriver;

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
