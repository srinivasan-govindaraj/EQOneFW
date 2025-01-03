package org.eq.utills;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eq.constants.Constants;
import org.eq.enums.Props;
import org.eq.exception.FWException;
import org.eq.exception.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class  Excel
{
    private Excel() {

    }
//need to change the method signature
    public static List<Map<Object, Object>> getData(String Sheetname) throws Exception {
        List<Map<Object, Object>> testcase = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(Constants.getTestData() + Utills.getKey(Props.TESTDATA));
        try (fileInputStream) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = workbook.getSheet(Sheetname);
            int row = xssfSheet.getLastRowNum();
            int col = xssfSheet.getRow(0).getLastCellNum();
            HashMap<Object, Object> map;
            for (int i = 1; i <= row; i++) {
                map = new HashMap<>();
                for (int j = 0; j < col; j++) {
                    String key = xssfSheet.getRow(0).getCell(j).getStringCellValue();
                    String value = xssfSheet.getRow(i).getCell(j).getStringCellValue();
                    map.put(key, value);


                }
                testcase.add(map);
            }



        } catch (FileNotFoundException e) {
            StackTraceElement[] stackTraceElements = e.getStackTrace();
            stackTraceElements[0] = new StackTraceElement("org.eq.Utills.Excel","getData","Excel.java",24);
            e.setStackTrace(stackTraceElements);
            throw new File("File not found exception", e);
        }
        return testcase;

    }
}
