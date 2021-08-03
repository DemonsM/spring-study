package com.ink.springframework.beans.factory.support;

import com.ink.springframework.beans.factory.config.BeanDefinition;

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

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
