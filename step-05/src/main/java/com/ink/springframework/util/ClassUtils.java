package com.ink.springframework.util;

import java.util.Objects;

/**
 * @author MT
 * @date 2021/8/5 10:41
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable throwable) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (Objects.isNull(classLoader)) {
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }
}
