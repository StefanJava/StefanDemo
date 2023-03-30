package com.stefan.thread.demo.singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/30 10:56
 * @version: 1.0
 */
public class Test {
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            EXECUTOR.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Singleton4.getSingleton());
            });
        }
        EXECUTOR.shutdown();
    }
}
