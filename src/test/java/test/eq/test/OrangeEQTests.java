package test.eq.test;


import com.aventstack.chaintest.conf.Configuration;
import com.aventstack.chaintest.conf.ConfigurationManager;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import dev.eq.annotation.EQFrameworkAnnotation;
import dev.eq.enums.Category;

import dev.eq.log.BaseLogger;
import test.eq.pages.Login;

import static dev.eq.factory.DriverManager.getDriver;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.api.Assertions;
import dev.eq.dataprovider.Jsonutill;
import dev.eq.enums.Props;
import dev.eq.factory.ReportManager;
import dev.eq.utills.Utills;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class OrangeEQTests extends BaseTests{
    private OrangeEQTests()
    {

    }
    @Test
    @EQFrameworkAnnotation(author = {"EQ","JaiSriram"},category ={Category.SMOKE,Category.REGRESSION})

    public void loginorm(Map<Object,Object>map){

    getDriver().get(Utills.getKey(Props.URL));
    getDriver().manage().window().maximize();
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    BaseLogger.logger.info(Jsonutill.get(Props.URL));
    System.out.println(Jsonutill.get(Props.URL));
    String title = new Login().enterUserName(map.get("uname").toString())
            .enterPassword(map.get("password").toString())
            .clickLogin()
            .welcome().getTitle();
    Assertions.assertThat(title).isEqualTo("OrangeHRM");
   map.forEach((k,v)-> System.out.println(k+":"+v));
    ReportManager.StartTest().pass(MarkupHelper.createUnorderedList(map).getMarkup());
    log.info("Method Completed");

}

    @EQFrameworkAnnotation(author = {"EQ","JaiSriram"},category ={Category.SMOKE,Category.REGRESSION})
    @Test
    public void loginorm1(Map<Object,Object>map)  {
        getDriver().get(Utills.getKey(Props.URL));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println(Jsonutill.get(Props.URL));
        String title = new Login().enterUserName(map.get("uname").toString())
                .enterPassword(map.get("password").toString())
                .clickLogin()
                .welcome().getTitle();
        Assertions.assertThat(title).isEqualTo("OrangeHRM");
        map.forEach((k,v)-> System.out.println(k+":"+v));
        ReportManager.StartTest().pass(MarkupHelper.createUnorderedList(map).getMarkup());

    }

@DataProvider(name = "eq",parallel = true)
    public Object[] getData() throws IOException {
    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
    XSSFSheet xssfSheet = workbook.getSheetAt(0);
    int row = xssfSheet.getLastRowNum();
    int col = xssfSheet.getRow(0).getLastCellNum();
    HashMap<Object,Object> map ;
    Object[] data = new Object[row];
    for(int i=1; i<=row;i++)
    {
        map = new HashMap<>();
        for(int j=0;j<col;j++)
        {
            String key= xssfSheet.getRow(0).getCell(j).getStringCellValue();
            String value = xssfSheet.getRow(i).getCell(j).getStringCellValue();
            map.put(key,value);
            data[i-1]=map;
        }
    }
    return  data;
}

}
