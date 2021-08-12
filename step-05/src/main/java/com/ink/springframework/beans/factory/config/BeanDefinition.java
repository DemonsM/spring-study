package com.ink.springframework.beans.factory.config;

import com.ink.springframework.beans.PropertyValues;

/**
 * @author MT
 * @date 2021/8/2 17:05
 */
public class BeanDefinition {
    private final Class<?> beanClass;
    private final PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
