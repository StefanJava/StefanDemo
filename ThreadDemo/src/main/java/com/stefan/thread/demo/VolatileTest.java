package com.stefan.thread.demo;

import java.util.concurrent.TimeUnit;

/**
 * @description: volatile test
 * @author: stefanyang
 * @date: 2023/3/30 10:31
 * @version: 1.0
 */
public class VolatileTest {
    private volatile static int a = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    a++;
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
