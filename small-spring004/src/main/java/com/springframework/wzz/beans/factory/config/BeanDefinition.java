package com.springframework.wzz.beans.factory.config;

import com.springframework.wzz.beans.PropertyValues;

/**
 * 定义存储对象
 *
 * 在 Bean 注册的过程中是需要传递 Bean 的信息，
 *      在几个前面章节的测试中都有所体现 new BeanDefinition(UserService.class, propertyValues);
 * 所以为了把属性一定交给 Bean 定义，所以这里填充了 PropertyValues 属性，
 *      同时把两个构造函数做了一些简单的优化，避免后面 for 循环时还得判断属性填充是否为空。
 */
public class BeanDefinition {
    private Class bean;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.bean = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass,PropertyValues ps){
        this.bean = beanClass;
        this.propertyValues = ps;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
