package dev.eq.annotation;

import dev.eq.enums.Category;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EQFrameworkAnnotation {

     String[] author();
     Category[] category();

}
