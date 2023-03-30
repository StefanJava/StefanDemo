package com.stefan.thread.demo.singleton;

/**
 * @description: 懒汉式-非线程安全
 * @author: stefanyang
 * @date: 2023/3/30 10:47
 * @version: 1.0
 */
public class Singleton2 extends Singleton {
    private static Singleton2 singleton;

    private Singleton2() {
    }

    public static Singleton2 getSingleton() {
        if (null == singleton) {
            singleton = new Singleton2();
        }
        return singleton;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
