package com.ink.springframework.beans.factory.support;

import com.ink.springframework.core.io.Resource;
import com.ink.springframework.core.io.ResourceLoader;

/**
 * @author MT
 * @date 2021/8/5 14:58
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);
}
