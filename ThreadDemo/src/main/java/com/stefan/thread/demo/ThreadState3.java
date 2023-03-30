package com.stefan.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程状态
 * @author: stefanyang
 * @date: 2023/3/30 14:37
 * @version: 1.0
 */
public class ThreadState3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadState3 threadState3 = new ThreadState3();
        Thread thread = new Thread(() -> {
            synchronized (threadState3) {
                try {
                    threadState3.wait();
                    Thread currentThread = Thread.currentThread();
                    Thread.State state = currentThread.getState();
                    System.out.println(state);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.State state = thread.getState();
        System.out.println("首次获取线程状态: " + state);

        TimeUnit.MILLISECONDS.sleep(1000);

        state = thread.getState();
        System.out.println("第二次获取线程状态: " + state);
        synchronized (threadState3) {
            threadState3.notifyAll();
        }
    }
}
