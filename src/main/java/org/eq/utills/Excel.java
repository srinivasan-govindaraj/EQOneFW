package org.eq.utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public final class  Excel {
    private Excel()
    {

    }

    public static List<Map<String,String>> getTestData() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/TestData/TestData.xlsx");

        return List.of();
    }

}
