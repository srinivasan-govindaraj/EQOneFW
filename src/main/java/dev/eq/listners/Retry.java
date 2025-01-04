package dev.eq.listners;

import dev.eq.utills.Utills;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry  implements IRetryAnalyzer {
    private int count =0;
    private int limit = Integer.parseInt(Utills.getValue("retrycount"));

    public Retry() throws Exception {
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean value = count<limit;
          count ++;
          return value;

    }
}
