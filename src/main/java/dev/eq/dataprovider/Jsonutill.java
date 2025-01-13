package dev.eq.dataprovider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.eq.constants.Constants;
import dev.eq.enums.Props;
import dev.eq.exception.Property;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Jsonutill {
    private static Map<Object,Object> CONFIG;
    private Jsonutill()
    {

    }

    static {
        try
        {
            CONFIG = new ObjectMapper().readValue(new File(Constants.getJsonconfig()), new TypeReference<Map<Object, Object>>() {
            });
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String get(Props key)  {
        if(Objects.isNull(key) || Objects.isNull(CONFIG.get(key.name().toLowerCase())))
        {
            throw new Property("Propery is not found in Json file "+ key);
        }
        return (String) CONFIG.get(key.name().toLowerCase());
    }
}
