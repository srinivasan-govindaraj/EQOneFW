package dev.eq.constants;

import dev.eq.enums.Props;
import dev.eq.utills.Utills;
import lombok.Getter;

@Getter
public final class Constants {
    private Constants() {}
    private static final String EQ = "SRIRAMAJAYAM";
    private static final String EQ1 = "OM MURUGA";
    private static final String CONFIG = System.getProperty("user.dir")+"/src/main/resources/config.properties";
    private static final String JSONCONFIG = System.getProperty("user.dir")+"/src/main/resources/JSONConfig.json";
    private static final String EXTENTREPORT = System.getProperty("user.dir")+"/test-output/Report/";
    private static final String TestData = System.getProperty("user.dir")+"/TestData/";
    private static int EXPLICITWAIT = 10;

    public static int getEXPLICITWAIT() {
        return EXPLICITWAIT;
    }

    public static String getEq()
    {
        return EQ;
    }
    public static String getEq1()
    {
        return EQ1;
    }

    public static String getCONFIG() {
        return CONFIG;
    }
    public static String getExtentreport()  {
        if(Utills.getKey(Props.NEWREPORT).equalsIgnoreCase("yes"))
        {
            return EXTENTREPORT+System.currentTimeMillis()+"/index.html";
        }
        else {
            return EXTENTREPORT+"index.html";
        }

    }

    public static String getJsonconfig() {
        return JSONCONFIG;
    }
    public static String getTestData()
    {
        return TestData;
    }

}
