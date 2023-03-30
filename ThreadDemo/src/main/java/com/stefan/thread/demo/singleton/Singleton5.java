package com.stefan.thread.demo.singleton;

/**
 * @description: 双重检索-线程安全
 * @author: stefanyang
 * @date: 2023/3/30 10:47
 * @version: 1.0
 */
public class Singleton5 extends Singleton {
    private static volatile Singleton5 singleton;

    private Singleton5() {
    }

    public static Singleton5 getSingleton() {
        if (null == singleton) {
            synchronized (Singleton5.class) {
                if (null == singleton) {
                    singleton = new Singleton5();
                }
            }
        }
        return singleton;
    }
}
