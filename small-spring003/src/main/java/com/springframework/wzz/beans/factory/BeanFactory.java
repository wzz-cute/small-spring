package com.springframework.wzz.beans.factory;

import com.springframework.wzz.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    //注入多参数
    Object getBean(String name, Object... args) throws BeansException;
}
