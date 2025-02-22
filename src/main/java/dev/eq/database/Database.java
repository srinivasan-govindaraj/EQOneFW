package dev.eq.database;

import javassist.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class Database {



    public static Map<String, Class<?>> getColumnInfo(ResultSet resultSet) throws SQLException {
        Map<String, Class<?>> columnInfo = new HashMap<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            int columnType = metaData.getColumnType(i);
            Class<?> javaType = getJavaType(columnType);
            columnInfo.put(columnName, javaType);
        }

        return columnInfo;
    }

    private static Class<?> getJavaType(int sqlType) throws SQLException {
        switch (sqlType) {
            case Types.INTEGER: return Integer.class;
            case Types.VARCHAR: return String.class;
            // Add more type mappings as needed
            default: return Object.class;
        }
    }
    public static Class<?> generateDynamicPojo(String className, Map<String, Class<?>> properties) throws CannotCompileException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass(className);

        for (Map.Entry<String, Class<?>> entry : properties.entrySet()) {
            String fieldName = entry.getKey();
            Class<?> fieldType = entry.getValue();

            CtField ctField = new CtField(pool.get(fieldType.getName()), fieldName, cc);
            cc.addField(ctField);

            cc.addMethod(CtNewMethod.getter("get" + capitalize(fieldName), ctField));
            cc.addMethod(CtNewMethod.setter("set" + capitalize(fieldName), ctField));
        }

        return cc.toClass();
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

/*<dependency>
    <groupId>org.javassist</groupId>
    <artifactId>javassist</artifactId>
    <version>3.30.0-GA</version>
</dependency>*/

//    https://rnacentral.org/help/public-database
}
