package com.eq.test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eq.pages.Login;
import static org.eq.utills.Utills.getKey;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.api.Assertions;
import org.eq.enums.Props;
import org.eq.factory.ReportManager;
import org.eq.report.Report;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class OrangeEQTests extends BaseTests{
    private OrangeEQTests()
    {

    }
@Test(dataProvider = "eq")
    public void loginorm(Map<Object,Object> map) throws Exception {
    String title = new Login().enterUserName(getKey(Props.UNAME))
            .enterPassword(getKey(Props.PASSWORD))
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
