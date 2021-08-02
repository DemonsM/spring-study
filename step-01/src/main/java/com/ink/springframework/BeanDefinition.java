package com.ink.springframework;

/**
 * @author MT
 * @date 2021/8/2 16:14
 */
public class BeanDefinition {
    private final Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
