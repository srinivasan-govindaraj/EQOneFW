package org.eq.utills;

public final class DynamicXpath {

    private DynamicXpath()
    {

    }
    public static String getXpath(String xapth,String replace)
    {
        return String.format(xapth,replace);

    }

}
