package dev.eq.props.converter;

import dev.eq.enums.Props;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringtoenumConverter implements Converter<Props> {
    @Override
    public Props convert(Method method, String value) {

        return Props.valueOf(method.getName().toUpperCase());
        // we can use enum
    }
}
