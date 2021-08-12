package com.ink.springframework.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.ink.springframework.beans.PropertyValue;
import com.ink.springframework.beans.factory.config.BeanDefinition;
import com.ink.springframework.beans.factory.config.BeanReference;
import com.ink.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.ink.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.ink.springframework.core.io.Resource;
import com.ink.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author MT
 * @date 2021/8/5 15:06
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            //判断元素
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }
            //判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) {
                continue;
            }

            //解析标签"bean"
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            //获取class 方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            //优先级Id > Name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = clazz.getSimpleName();
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                //判断元素
                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }
                //判断对象
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                //解析标签"property"
                Element property = (Element) bean.getChildNodes().item(j);
                String attName = property.getAttribute("name");
                String attValue = property.getAttribute("value");
                String attRef = property.getAttribute("ref");

                //获取属性值 引入对象、值对象
                Object value = StrUtil.isNotEmpty(attRef) ? new BeanReference(attName) : attValue;

                //创建属性
                PropertyValue propertyValue = new PropertyValue(attName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeanException("Duplicate name[" + beanName + "] is not allowed!");
            }
            //注册Bean
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
