package com.stefan.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/13 13:59
 * @version: 1.0
 */
public class Demo001 {
    public static void main(String[] args) {
        method2();

    }

    private static void method2() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被main线程中断");
                    break;
                }
            }
        });

        thread1.start();
        System.out.println("========main============");
        thread1.interrupt();
    }

    private static void method1() {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        System.out.println("========main============");
        thread1.interrupt();
    }
}
