package com.stefan.thread.demo.singleton;

/**
 * @description: 懒汉式-线程安全
 * @author: stefanyang
 * @date: 2023/3/30 10:47
 * @version: 1.0
 */
public class Singleton3 extends Singleton {
    private static Singleton3 singleton;

    private Singleton3() {
    }

    public static synchronized Singleton3 getSingleton() {
        if (null == singleton) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
