package com.stefan.proxy.dynamicproxy.jdk;

import com.stefan.proxy.dynamicproxy.jdk.DebugInvocationHandler;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    public static Object getProxy(Object object) {

        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new DebugInvocationHandler(object));
    }

}
