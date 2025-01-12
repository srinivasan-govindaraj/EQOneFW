package dev.eq.utills;

import dev.eq.props.Configs;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import dev.eq.constants.Constants;
import dev.eq.enums.Props;
import dev.eq.exception.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static dev.eq.utills.Utills.getConfigOwner;

public final class  Excel
{
    private Excel() {

    }
//need to change the method signature
    public static List<Map<Object, Object>> getData(String Sheetname) throws Exception {
        List<Map<Object, Object>> testcase = new ArrayList<>();// singleton pattern
       // Logger.getAnonymousLogger().info("URL is fecthed from the owner library as printed as below ");

       // Logger.getAnonymousLogger().info("URL is fecthed from normal properties read method");
       // String path = Constants.getTestData() + Utills.getKey(Props.TESTDATA);
        //Logger.getAnonymousLogger().info(path);
        FileInputStream fileInputStream = new FileInputStream(Constants.getTestData()+Utills.getKey(Props.TESTDATA));
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
            e.printStackTrace();
            throw new File("File not found exception", e);
        }
        return testcase;

    }
}
