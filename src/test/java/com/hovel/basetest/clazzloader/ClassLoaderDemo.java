package com.hovel.basetest.clazzloader;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class ClassLoaderDemo {

    @Test
    public void test(){
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

        System.out.println(classLoader.equals(Thread.currentThread().getContextClassLoader()));
    }
}
