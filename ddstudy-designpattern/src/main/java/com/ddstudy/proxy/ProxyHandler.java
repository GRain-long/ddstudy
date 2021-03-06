package com.ddstudy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Classname ProxyHandler
 * @Description JDK动态代理
 * @Date 2020/6/28
 * @Author Grain Rain
 */
public class ProxyHandler implements InvocationHandler {

    private final Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("Before invoke :" + method.getName());

        method.invoke(object, args);

        System.out.println("After invoke :" + method.getName());

        return null;
    }
}
