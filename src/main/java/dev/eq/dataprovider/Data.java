package dev.eq.dataprovider;

import org.testng.annotations.DataProvider;

public class Data {
    public Data(String eq, String cutie) {
        this.a=eq;
        this.b=cutie;
    }
/*public Data()
{
    either have default public constructor or static method to achieve in other class
}*/

    public String getA() {
        return a;
    }


    public String getB() {
        return b;
    }


    private String a;
    private String b;

    @DataProvider
    public static Data[] getData3()
    {
        return new Data[]
                {
                        new Data("eq","cutie"), new Data("good","soul")

                };
    }

}
