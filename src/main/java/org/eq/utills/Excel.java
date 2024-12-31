package org.eq.utills;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eq.constants.Constants;
import org.eq.enums.Props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class  Excel {
    private Excel()
    {

    }

    public static List<Map<String,String>> getTestData() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(Constants.getTestData()+Utills.getKey(Props.TESTDATA));
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

        return List.of();
    }

}
