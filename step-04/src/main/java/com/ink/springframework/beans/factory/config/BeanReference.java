package com.ink.springframework.beans.factory.config;

/**
 * @author MT
 * @date 2021/8/4 15:07
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
