package com.bigdata.juc.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/14 11:41
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {

//        runAsync();
//        supplyAsync();
        whenCompleteAsync();

    }

    private static void whenCompleteAsync() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("run end ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(new Random().nextInt() % 2 >= 0) {
                int i = 12/2;
            }
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("执行完成！");
            }
        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {

                System.out.println("执行失败" + t.getMessage());
                return null;
            }
        });

//        future.whenComplete(new BiConsumer<Void, Throwable>() {
//            @Override
//            public void accept(Integer integer, Throwable throwable) {
//                System.out.println("执行完成！");
//            }
//        }).exceptionally(new Function<Throwable, Integer>() {
//            @Override
//            public Integer apply(Throwable throwable) {
//                System.out.println("执行失败" + throwable.getMessage());
//                return null;
//            }
//        });

    }

    private static String supplyAsync() throws Exception {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world!";
        });

        // 获取返回值
        String result = supplyAsync.get();
        return result;
    }

    private static void runAsync() {
        CompletableFuture.runAsync(() -> {

        });
    }
}
