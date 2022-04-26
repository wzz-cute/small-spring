package com.springframework.wzz.beans.factory.support;

import com.springframework.wzz.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个获取单例的类
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonMap = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonMap.get(name);
    }

    protected void addSingleton(String name, Object singletonBean) {
        singletonMap.put(name, singletonBean);
    }
}
