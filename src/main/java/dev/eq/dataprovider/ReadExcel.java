package dev.eq.dataprovider;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookProvider;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadExcel {
    @Test(dataProvider = "getData4")
    public void testdata1(Map<Object,Object> map) //Object[number of times][number of columns->see arguments]
    {
        System.out.println("I love you eq ");
        map.forEach((k,v)-> System.out.println(k+":"+v));
        /*for(Map.Entry<Object,Object> m : map.entrySet())
        {
            System.out.println("Head:"+m.getKey());
            System.out.println("Value:"+m.getValue());
        }*/

    }

    @DataProvider
    public Object[][] getData3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet Sheet = workbook.getSheetAt(0);
        Object[][] data = new Object[Sheet.getLastRowNum()][Sheet.getRow(0).getLastCellNum()];
        int row = Sheet.getLastRowNum();
        int col = Sheet.getRow(0).getLastCellNum();
        for(int i=1;i<=row;i++)
        {
            for (int j=0;j<col;j++)
            {
        data[i-1][j] = Sheet.getRow(i).getCell(j).getStringCellValue();
            }
        }

        return data;
    }
    @DataProvider
    public Object[] getData4() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet Sheet = workbook.getSheetAt(0);
        Object[] data = new Object[Sheet.getLastRowNum()];
        HashMap<Object,Object> map ;
        int row = Sheet.getLastRowNum();
        int col = Sheet.getRow(0).getLastCellNum();
        for(int i=1;i<=row;i++)
        {
            map=new HashMap<>();
            for (int j=0;j<col;j++)
            {
                String key = Sheet.getRow(0).getCell(j).getStringCellValue();//0
                String value = Sheet.getRow(i).getCell(j).getStringCellValue();//
                map.put(key,value);
                data[i-1]=map;
            }
        }

        return data;
    }

}
