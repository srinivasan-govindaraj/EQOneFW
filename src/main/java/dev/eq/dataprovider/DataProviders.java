package dev.eq.dataprovider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import static dev.eq.utills.Excel.getData;

public final class DataProviders {
    private static List<Map<Object,Object>> eq = new ArrayList<>();
    @DataProvider(parallel = false)
    public static Object[] getTestData(Method method) throws Exception {
        String testname = method.getName();
        if(eq.isEmpty())
        {
            eq= getData("Data");
        }
        List<Map<Object,Object>> listitr = new ArrayList<>();
        for(int i=0;i< eq.size();i++)
        {
            if(eq.get(i).get("testname").equals(testname) && (eq.get(i).get("execute").equals("yes")))
                {
                    listitr.add(eq.get(i));
                }

        }
return listitr.toArray();
    }



}
