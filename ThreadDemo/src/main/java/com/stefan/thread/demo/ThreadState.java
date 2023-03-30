package com.stefan.thread.demo;

/**
 * @description: 线程状态
 * @author: stefanyang
 * @date: 2023/3/30 14:37
 * @version: 1.0
 */
public class ThreadState {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getState());
        });
        Thread.State state = thread.getState();
        System.out.println(state);
        thread.start();
    }
}
