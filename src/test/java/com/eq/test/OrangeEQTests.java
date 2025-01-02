package com.eq.test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eq.pages.Login;

import static org.eq.factory.DriverManager.getDriver;
import static org.eq.utills.Utills.getKey;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.api.Assertions;
import org.eq.dataprovider.Jsonutill;
import org.eq.enums.Props;
import org.eq.factory.ReportManager;
import org.eq.report.Report;
import org.eq.utills.DataProviders;
import org.eq.utills.Utills;
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

@Test()
    public void loginorm(Map<Object,Object>map) throws Exception {
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
    @Test
    public void loginorm1(Map<Object,Object>map) throws Exception {
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
