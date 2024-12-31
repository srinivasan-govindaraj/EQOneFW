package org.eq.factory;

import com.aventstack.extentreports.ExtentTest;

public class ReportManager {
    private static ThreadLocal<ExtentTest> extTest=new ThreadLocal<>();

    public static ExtentTest StartTest()  // we can make it as inside the package remember , if we done we can remove the
            //public keyword and default --> it will scope with in the package
    {
        return extTest.get();
    }
    public static void setExtTest(ExtentTest extTest1)
    {
        extTest.set(extTest1);
    }
    public static void flushTest()
    {
        extTest.remove();
    }


}



