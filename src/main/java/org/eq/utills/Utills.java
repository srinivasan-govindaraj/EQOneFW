package org.eq.utills;

import org.eq.constants.Constants;
import org.eq.enums.Props;
import org.eq.exception.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class Utills {
   private static final Properties properties= new Properties();
   private static final Map<String,String> config = new HashMap<>();
    static FileInputStream file;

    static {
        try {
            file = new FileInputStream(Constants.getCONFIG());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Utills()  {
    }
    static {
        try {
            properties.load(file);

            for(Map.Entry<Object,Object> entry : properties.entrySet())
            {
                config.put((String) entry.getKey(), (String) entry.getValue());
            }
           /* for (Object key : properties.keySet())
            {
                config.put(String.valueOf(key),String.valueOf(properties.getProperty((String) key)));

            }*/
          //  properties.entrySet().forEach(entry -> config.put((String) entry.getKey(), (String) entry.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Hasmap--> Optional
    public static String getKey(Props key) {
        if(Objects.isNull(key) || Objects.isNull(config.get(key.name().toLowerCase())))
        {
            throw new Property("Propery issue" + key + "is not working");
        }
        return config.get(key.name().toLowerCase().trim());
    }
//Hashtable little slow -- thread safe
    public static String getValue(String key)  {
        if (Objects.isNull(properties.getProperty(key)) || Objects.isNull(key) )
        {
            throw new Property("Property issue" + key + "is not working");
        }
        return properties.getProperty(key);
    }
}
