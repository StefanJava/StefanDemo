package com.stefan.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description: demo001
 * @author: stefanyang
 * @date: 2023/3/6 20:21
 * @version: 1.0
 */
@Slf4j
public class Demo01 {
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

        supplyAsyncHandle(executor);

        executor.shutdown();
    }

    /**
     * handle()-可获取返回值，可以改变返回值
     * @param executor executor
     */
    private static void supplyAsyncHandle(ThreadPoolExecutor executor) {
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("supplyAsync()方法");
                int i = 10 * 2;
                log.debug("异步任务结果 :{}", i);
                return i;
            }, executor).handle((res, ex) -> {
                if (ex != null) {
                    return -1;
                }
                res = res * 2;
                log.debug("handle()处理结果 :{}", res);
                return res;
            });

            log.debug("main方法");
            // 阻塞
            log.debug("计算结果为: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * whenComplete() - 可获取返回值 不可改变返回值
     * exceptionally - 不可获取返回值，可改变返回值，发生异常时，可返回默认值
     * @param executor executor
     */
    private static void supplyAsyncWhenComplete(ThreadPoolExecutor executor) {
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("supplyAsync()方法");
                int i = 10 * 2;
                log.debug("异步任务结果 :{}", i);
                return i;
            }, executor).whenComplete((res, ex) -> {
                log.debug("{} - {}", res, ex);
            }).exceptionally((ex) -> 10);

            log.debug("main方法");
            // 阻塞
            log.debug("计算结果为: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: CompletableFuture.runAsync()  无返回值
     *
     * @param: executor
     * @return:
     * @auther: stefanyang
     * @date: 2023/3/6 21:00
     */
    private static void runAsync(ThreadPoolExecutor executor) {

        CompletableFuture.runAsync(() -> {
            log.debug("runAsync()方法");
            int i = 10 / 2;
            log.debug("运行结果: {}", i);
        }, executor);

    }
}
