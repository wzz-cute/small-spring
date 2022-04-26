package com.springframework.wzz.beans.factory.config;

/**
 * 定义存储对象
 */
public class BeanDefinition {
    private Class bean;

    public BeanDefinition(Class beanClass) {
        this.bean = beanClass;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }
}
