package com.springframework.wzz.beans.factory.config;

/**
 * 存储 代表自定义类型
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
