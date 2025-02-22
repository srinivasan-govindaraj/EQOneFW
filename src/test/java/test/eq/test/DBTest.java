package test.eq.test;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static dev.eq.database.Database.generateDynamicPojo;
import static dev.eq.database.Database.getColumnInfo;

public class DBTest {
    @Test
    public void dbTest() throws SQLException, NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Class<?>> dynamicClasses = new HashMap<>();

        // Execute query 1
        try (Connection conn1 = DriverManager.getConnection("jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs", "reader", "NWDMCE5xdipIjRrp");
             Statement stmt1 = conn1.createStatement();
             ResultSet rs1 = stmt1.executeQuery("SELECT upi,taxid,ac FROM xref WHERE ac IN ('OTTHUMT00000106564.1', 'OTTHUMT00000416802.1')")) {

            Map<String, Class<?>> columnInfo1 = getColumnInfo(rs1);
            Class<?> dynamicClass1 = generateDynamicPojo("DynamicPojo1", columnInfo1);
            dynamicClasses.put("DB1", dynamicClass1);
        }

        // Execute query 2
        try (Connection conn2 = DriverManager.getConnection("jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs", "reader", "NWDMCE5xdipIjRrp");
             Statement stmt2 = conn2.createStatement();
             ResultSet rs2 = stmt2.executeQuery("SELECT upi,taxid,ac FROM xref WHERE ac IN ('OTTHUMT00000106564.1', 'OTTHUMT00000416802.1')")) {

            Map<String, Class<?>> columnInfo2 = getColumnInfo(rs2);
            Class<?> dynamicClass2 = generateDynamicPojo("DynamicPojo2", columnInfo2);
            dynamicClasses.put("DB2", dynamicClass2);
        }

        // Now dynamicClasses map contains the dynamic POJO classes for both queries
        // You can use these classes throughout your execution

        // Example of using a dynamic class
        Class<?> db1Class = dynamicClasses.get("DB1");
        Method[] methods = db1Class.getMethods();

        System.out.println("Methods in the dynamically created class:");
        for (Method method : methods) {
            System.out.println(method.toString());
        }
       /* Object db1Instance = db1Class.newInstance();
        db1Class.getMethod("setColumnName", String.class).invoke(db1Instance, "Value");
        String value = (String) db1Class.getMethod("getColumnName").invoke(db1Instance);
        System.out.println("Value from DB1: " + value);*/
    }

    }

