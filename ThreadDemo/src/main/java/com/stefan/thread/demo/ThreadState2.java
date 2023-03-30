package com.stefan.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程状态
 * @author: stefanyang
 * @date: 2023/3/30 14:37
 * @version: 1.0
 */
public class ThreadState2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("排队使用锁");
            synchronized (ThreadState2.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    Thread.State state = Thread.currentThread().getState();
                    System.out.println(state);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        synchronized (ThreadState2.class) {
            Thread.State state = thread.getState();
            System.out.println("首次获取线程状态: " + state);

            TimeUnit.MILLISECONDS.sleep(1000);

            state = thread.getState();
            System.out.println("第二次获取线程状态: " + state);
        }
        TimeUnit.MILLISECONDS.sleep(100);
        Thread.State state = thread.getState();
        System.out.println("第三次获取线程状态: " + state);
    }
}
