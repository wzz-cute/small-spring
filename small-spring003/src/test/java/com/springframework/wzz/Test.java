package com.springframework.wzz;

import com.springframework.wzz.beans.factory.config.BeanDefinition;
import com.springframework.wzz.beans.factory.support.CglibSubclassingInstantiationStrategy;
import com.springframework.wzz.beans.factory.support.DefaultListableBeanFactory;
import com.springframework.wzz.beans.factory.support.InstantiationStrategy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

@SpringBootTest
public class Test {

    /**
     * 大致流程就是，有一个注册容器和单例容器，bean里面先会添加金注册容器中，
     * 获取单例时发现单例容器中没有当前实例，然后去注册容器中获取，再添加进单例容器中
     * 下次获取单例容器就有值了
     * 其中还用到了模板模式，大致就是当前本类用到的方法给子类实现，
     * 本类不需要知道他的实现，只需要调用即可
     *
     * 本示例即为，如果有入参等参数使用反射或者cglib代理将对象实例化出来
     */
    @org.junit.jupiter.api.Test
    public void test_BeanFactory() {
        //初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService);
        userService.queryUserInfo();

        //第二次获取bean 单例
        UserService userService_singleton = (UserService) beanFactory.getBean("userService","wzz");
        System.out.println(userService_singleton);
        userService_singleton.queryUserInfo();
    }

    private InstantiationStrategy strategy = new CglibSubclassingInstantiationStrategy();

    @org.junit.jupiter.api.Test
    public void createCglibTest(){
        Class classzz = UserService.class;
        Object[] vals = {"wzz"};

        BeanDefinition beanDefinition = new BeanDefinition(classzz);

        Constructor constructor = null;
        for (Constructor declaredConstructor : classzz.getDeclaredConstructors()) {
            if (declaredConstructor.getParameterTypes().length == vals.length){
                constructor = declaredConstructor;
                break;
            }
        }

        UserService userService = (UserService) strategy.instantiate(beanDefinition, "userService", constructor, vals);

        userService.queryUserInfo();
    }

    @org.junit.jupiter.api.Test
    public void test_newInstance(){
        try {
            UserService userService = UserService.class.newInstance();
            userService.queryUserInfo();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void test_constructor(){
        Class classzz = UserService.class;
        try {
            Constructor constructor = classzz.getDeclaredConstructor(String.class);
            UserService wzz = (UserService) constructor.newInstance("wzz");
            wzz.queryUserInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void test_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"wzz"});
        System.out.println(obj);
    }

}
