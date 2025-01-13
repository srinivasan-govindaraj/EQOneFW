package dev.eq.utills;

import dev.eq.constants.Constants;
import dev.eq.enums.Props;
import dev.eq.exception.Property;

import dev.eq.props.Configs;
import org.aeonbits.owner.ConfigCache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public final class Utills {
    /**
     * This is class can be replaced with @config interface
     * Implmentation chekck @Excel class
     * @see dev.eq.utills.Excel    major use avoid more lines of code and exception handling
     * ToDO: we can avoid using this class ,just keeping for the reference purpose
     */
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
           // System.exit(0);
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

    public static String getConfigOwner(Props props)
    {
        return ConfigCache.getOrCreate(Configs.class).getValue(props.name());
    }
}
