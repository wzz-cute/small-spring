package com.springframework.wzz.beans.factory.config;

/**
 * 单例注册接口定义和实现
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例bean对象
     * @param name
     * @return
     */
    Object getSingleton(String name);
}
