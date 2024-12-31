package org.eq.listners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class MethodInterceptor  implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
       List<IMethodInstance> result = new ArrayList<IMethodInstance>();
       result.add(list.get(0));
        return result;
    }
}
