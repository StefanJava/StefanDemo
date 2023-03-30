package com.stefan.thread.demo.singleton;

/**
 * @description: 双重检索-非线程安全
 * @author: stefanyang
 * @date: 2023/3/30 10:47
 * @version: 1.0
 */
public class Singleton4 extends Singleton {
    private static Singleton4 singleton;

    private Singleton4() {
    }

    public static Singleton4 getSingleton() {
        if (null == singleton) {
            synchronized (Singleton4.class) {
                if (null == singleton) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
