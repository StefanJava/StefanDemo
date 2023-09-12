package com.stefan.proxy;

import com.stefan.proxy.dynamicproxy.cglib.CglibProxyFactory;
import com.stefan.proxy.dynamicproxy.jdk.JdkProxyFactory;
import com.stefan.proxy.service.AliSmsService;
import com.stefan.proxy.service.SmsService;
import com.stefan.proxy.service.impl.SmsServiceImpl;
import com.stefan.proxy.staticproxy.SmsProxy;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("hello world");
        System.out.println("==================================");

        smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("你好");
        System.out.println("==================================");

        AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        proxy.send("hello stefan");
    }
}
