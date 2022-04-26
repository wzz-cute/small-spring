package com.springframework.wzz.beans.factory;

import com.springframework.wzz.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
