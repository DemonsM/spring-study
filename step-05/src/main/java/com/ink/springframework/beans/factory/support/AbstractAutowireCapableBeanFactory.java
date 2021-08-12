package com.ink.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.ink.springframework.beans.BeansException;
import com.ink.springframework.beans.PropertyValue;
import com.ink.springframework.beans.PropertyValues;
import com.ink.springframework.beans.factory.config.BeanDefinition;
import com.ink.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author MT
 * @date 2021/8/2 17:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private final InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean;
        //try {
        //    bean = beanDefinition.getBeanClass().newInstance();
        //} catch (InstantiationException | IllegalAccessException e) {
        //    throw new BeansException("Instantiation of bean failed", e);
        //}
        bean = createBeanInstance(beanDefinition, beanName, args);
        applyPropertyValues(bean, beanName, beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructor = beanClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructor) {
            if (Objects.nonNull(args) && constructor.getParameterTypes().length == args.length) {
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(Object bean, String beanName, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
