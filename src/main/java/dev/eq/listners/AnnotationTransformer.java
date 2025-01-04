package dev.eq.listners;

import dev.eq.utills.DataProviders;
import dev.eq.utills.Utills;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        //IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);

        annotation.setEnabled(true);
       // annotation.setInvocationCount(1);
        try {
            if(!Utills.getValue("retrycount").isBlank())
            {
                annotation.setRetryAnalyzer(Retry.class);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        annotation.setDataProvider("getTestData");
        annotation.setDataProviderClass(DataProviders.class);
    }
}
