package com.stefan.thread.demo.singleton;

/**
 * @description: 静态内部类-线程安全
 * @author: stefanyang
 * @date: 2023/3/30 10:47
 * @version: 1.0
 */
public class Singleton6 extends Singleton {
    private static class SingletonHolder {
        private static final Singleton6 singleton = new Singleton6();
    }

    private Singleton6() {
    }

    public static Singleton6 getSingleton() {
        return SingletonHolder.singleton;
    }
}
