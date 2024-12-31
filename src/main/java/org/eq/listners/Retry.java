package org.eq.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry  implements IRetryAnalyzer {
    int count =0;
    int limit =2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<limit)
        {
          count ++;
          return true;
        }
        return false;
    }
}
