package test.eq.test;

import static org.assertj.core.api.Assertions.*;

import dev.eq.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import static dev.eq.factory.DriverManager.getDriver;

public class LoginPageTests extends BaseTests{

    @Test
    public void eq()
    {
       /* List<String> a = List.of("one,two,three");
        List<String> b = List.of("one,two,threee");
       // System.out.println(Assertions.assertThat(a).isEqualTo(b));
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(a).isEqualTo(b);
        soft.assertAll();*/




        getDriver().findElement(By.name("q")).sendKeys(Constants.getEq(), Keys.ENTER);
        System.out.println("Case 1");
        System.out.println(getDriver().getTitle());
        assertThat(getDriver().getTitle())
                .as("eq").isNotNull()
                .containsIgnoringCase("google search")
                .matches("\\w.*")
                .hasSizeBetween(15,100);
        getDriver().findElements(By.xpath("//a"))
                .stream()
                .map(WebElement::getText)
                .distinct()
                .sorted()
                .forEach(System.out::println);

    }




}
