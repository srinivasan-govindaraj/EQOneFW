package dev.eq.listners;

import dev.eq.exception.File;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dev.eq.utills.Excel.getData;

public class MethodInterceptor  implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        List<IMethodInstance> result = new ArrayList<>();
        try {
            List<Map<Object,Object>> testcase = getData("Test");

            for(int i=0;i< list.size();i++)
            {
                for(int j=0;j< testcase.size();j++)
                {
                    if(list.get(i).getMethod().getMethodName().equalsIgnoreCase((String) testcase.get(j).get("testname")) && (testcase.get(j).get("execute").equals("yes")))
                    {
                          list.get(i).getMethod().setDescription((String) testcase.get(j).get("testdescription"));
                          result.add(list.get(i));

                    }
                }
            }
        } catch (File e) {
            //Logger.getAnonymousLogger().info("File not found Exception");
            throw new File("File not found Execption");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
