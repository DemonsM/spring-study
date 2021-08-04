package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.BeansException;
import com.ink.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @author MT
 * @date 2021/8/3 14:12
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            if (Objects.isNull(constructor)) {
                return beanClass.getDeclaredConstructor().newInstance();
            } else {
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
