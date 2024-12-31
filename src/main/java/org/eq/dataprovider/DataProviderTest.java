package org.eq.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    //if we extend the data provider class
    // from other class also we can use method
    //Object[][]--> Two dimensional 1-> Number of iteration 2-> number of data

    //@Test(dataProvider = "getData3", dataProviderClass = Data.class)
    public void testdata(Data d)
    {
        System.out.println("I love you eq ");
        System.out.println(d.getA());
        System.out.println(d.getB());
    }

    @Test(dataProvider = "getData3")
    public void testdata1(String a)
    {

        System.out.println("I love you eq ");

    }

    @Test(dataProvider = "getData3")
    public void testdata2(Data d )
    {
        System.out.println(d.getA());
        System.out.println("I love you eq ");
        System.out.println(d.getB());

    }

    // can be string
  @DataProvider
    public String[] getData()
  {
      String [] a = {"Eq","is","my","world"};
      return a;
  }
//Object super data type
    @DataProvider
    public Object[][] getData1()
    {
        return new Object[][]
                {
                        {"Eq"},
                        {"is"},
                        {"My everything"}

                };
    }
// we can use the Class
    @DataProvider
    public static Object[] getData3(Method m)
    {
        if(m.getName().equalsIgnoreCase("testdata2"))
        {
            return new Data[]
                    {
                            new Data("eq","cutie"), new Data("good","soul")

                    };
        }
        else if(m.getName().equalsIgnoreCase("testdata1"))
        {
            return new Object[][]
                    {
                            {"Eq"},
                            {"is"},
                            {"My everything"}

                    };
        }


        return null;
    }

}
