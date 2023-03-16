package com.stefan.thread.demo;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/13 14:15
 * @version: 1.0
 */
public class SynchronizedDemo {
    Object object = new Object();
    public void method1() {
        synchronized (object) {

        }
    }
}
