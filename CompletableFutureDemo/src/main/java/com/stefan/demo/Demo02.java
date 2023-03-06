package com.stefan.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description: 两个任务编排
 * @author: stefanyang
 * @date: 2023/3/6 21:51
 * @version: 1.0
 */
@Slf4j
public class Demo02 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

        executor.shutdown();
    }

    private static void thenApplyAsync(ThreadPoolExecutor executor) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("supplyAsync()方法");
            int i = 10 * 2;
            log.debug("异步任务结果 :{}", i);
            return i;
        }, executor).thenApplyAsync((res) -> {
            res = res * 2;
            log.debug("任务2开始 :{}", res);
            return res;
        }, executor);


        log.debug("main方法");
        try {
            log.debug("执行结果: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void thenAcceptAsync(ThreadPoolExecutor executor) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("supplyAsync()方法");
            int i = 10 * 2;
            log.debug("异步任务结果 :{}", i);
            return i;
        }, executor).thenAcceptAsync((res) -> {
            log.debug("任务2开始 :{}", res * 2);
        }, executor);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void thenRunAsync(ThreadPoolExecutor executor) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("supplyAsync()方法");
            int i = 10 * 2;
            log.debug("异步任务结果 :{}", i);
            return i;
        }, executor).thenRunAsync(() -> {
            log.debug("任务2开始");
        }, executor);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
