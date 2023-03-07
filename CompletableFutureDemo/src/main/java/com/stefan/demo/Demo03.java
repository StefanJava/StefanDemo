package com.stefan.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description: 三任务编排
 * @author: stefanyang
 * @date: 2023/3/6 22:07
 * @version: 1.0
 */
@Slf4j
public class Demo03 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

        /*CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            log.debug("任务1开始");
            int i = 40 * 3;
            log.debug("任务2结束");
            return i;
        }, executor);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            log.debug("任务2开始");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("任务2结束");
            return 40;
        }, executor);*/

        // 一、任务1和任务2都完成后，才执行任务3
        // 无感应结果  无返回值
        /*CompletableFuture<Void> future = future1.runAfterBothAsync(future2, () -> {
            log.debug("任务三开始");
        }, executor);*/

        // 能感应到结果  无返回值
        /*CompletableFuture<Void> future = future1.thenAcceptBothAsync(future2, (res1, res2) -> {
            log.debug("任务3开始,接收到任务1结果: {}, 任务2结果: {}", res1, res2);
        }, executor);*/

        // 能接受到结果  有返回值
        /*CompletableFuture<Integer> future = future1.thenCombineAsync(future2, (res1, res2) -> {
            log.debug("任务3开始, 接收到任务1结果: {}, 任务2结果: {}", res1, res2);
            return res1 + res2;
        }, executor);*/

        // 二、任务1和任务2有一个完成，就执行任务3
        // 不能接收到结果  无返回值
        /*CompletableFuture<Void> future = future1.runAfterEitherAsync(future2, () -> {
            log.debug("任务3开始");
        }, executor);

        log.debug("main");

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        // 能接收到结果  无返回值
        /*CompletableFuture<Void> future = future1.acceptEitherAsync(future2, (res) -> {
            log.debug("任务3开始, 接受到结果: {}", res);
        }, executor);

        log.debug("main");

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        // 能接收结果  有返回值
        /*CompletableFuture<Integer> future = future1.applyToEitherAsync(future2, (res) -> {
            log.debug("任务3开始, 接受到结果: {}", res);
            return res * 2;
        }, executor);

        log.debug("main");

        try {
            log.debug("任务3结果: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        // 三、多任务的编排
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1000);
                System.out.println("查询商品图片信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello.jpg";
        }, executor);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性信息");
            return "黑色+256G";
        }, executor);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("查询商品介绍信息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "华为...";
        }, executor);

        // 所有任务都执行完
        /*CompletableFuture<Void> future = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        // 有一个任务完成，即开始执行
        CompletableFuture<Object> future = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        try {
            log.debug("编排任务结果: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();

    }

    // 一个实际的例子
    /*public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();

        //1、sku详细信息 sku_info
        SkuInfoEntity skuInfo = getById(skuId);
        skuItemVo.setInfo(skuInfo);

        //2、sku 图片信息 sku_img
        List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
        skuItemVo.setImages(images);

        //3、spu 销售属性组合
        List<SkuItemSaleAttrVo> saleAttr = skuSaleAttrValueService.getSaleAttrBySpuId(skuInfo.getSpuId());
        skuItemVo.setSaleAttr(saleAttr);

        //4、spu 的介绍
        SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
        skuItemVo.setDesc(spuInfoDesc);

        //5、spu 规格参数信息
        List<SpuItemAttrGroupVo> groupAttrs = attrGroupService.getAttrGroupWithAttrsBySpuId(skuInfo.getSpuId(),skuInfo.getCatalogId());
        skuItemVo.setGroupAttrs(groupAttrs);

        return skuItemVo;
    }

    // 异步编排后
    private SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();

        *//**
         * 3、4、5需要依赖1的运行结果，需要返回skuInfo后从中获取spuId和catalogId
         * 而2不需要依赖1的运行结果
         *//*

        //1、sku详细信息 sku_info
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            SkuInfoEntity skuInfo = getById(skuId);
            skuItemVo.setInfo(skuInfo);
            return skuInfo;
        }, executor);

        //2、sku 图片信息 sku_img  2不需要等待上边1的执行结果
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(images);
        }, executor);

        //下边的3、4、5都需要上边1的执行结果
        //所以下边的3、4、5都是基于上边1的执行结果 infoFuture 开始的
        //都是以infoFuture.thenAcceptAsync(skuInfo -> {})开始的
        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //3、spu 销售属性组合  3
            List<SkuItemSaleAttrVo> saleAttr = skuSaleAttrValueService.getSaleAttrBySpuId(skuInfo.getSpuId());
            skuItemVo.setSaleAttr(saleAttr);
            System.out.println(saleAttr);
        }, executor);

        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //4、spu 的介绍
            SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
            skuItemVo.setDesc(spuInfoDesc);
        }, executor);

        CompletableFuture<Void> attrGroupFuture = infoFuture.thenAcceptAsync(skuInfo -> {
            //5、spu 规格参数信息
            List<SpuItemAttrGroupVo> groupAttrs = attrGroupService.getAttrGroupWithAttrsBySpuId(skuInfo.getSpuId(),skuInfo.getCatalogId());
            System.out.println(groupAttrs);
            skuItemVo.setGroupAttrs(groupAttrs);
        }, executor);

        //等待所有任务完成
        try {
            CompletableFuture.allOf(saleAttrFuture,descFuture,attrGroupFuture,imageFuture).get();
        } catch (InterruptedException e) {
            log.error("查询商品详情异步编排错误: ");
            log.error(e.getMessage() );
        } catch (ExecutionException e) {
            log.error(e.getMessage() );
        }
        return skuItemVo;
    }*/

}
