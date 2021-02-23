package com.bigdata.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 15:27
 */
public class FutureDemo {

    public static void main(String[] args) throws Exception {

        // 没有返回值的异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "runAsync");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("111");
        completableFuture.get();

        // 有返回值的异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return 1024;
        });

//        System.out.println(completableFuture2.get());
        CompletableFuture<Integer> exceptionally = completableFuture2.whenComplete((t, u) -> {
            System.out.println("t->" + t); //正常的返回值
            System.out.println("u->" + u); //打印异常信息
        }).exceptionally(e -> {
            e.printStackTrace();
            return 500;
        });

//        exceptionally.thenAccept()

        System.out.println(exceptionally.get());
    }
}
