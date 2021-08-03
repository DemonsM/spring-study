package com.ink.springframework.beans.factory.config;

/**
 * @author MT
 * @date 2021/8/2 17:05
 */
public class BeanDefinition {
    private final Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
