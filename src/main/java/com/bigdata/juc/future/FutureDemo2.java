package com.bigdata.juc.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/11/26 12:59
 */
public class FutureDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello future", new Executor() {
            @Override
            public void execute(Runnable command) {
                System.out.println("future");
            }
        });

        String s = future2.get();
        System.out.println(s);
        System.out.println(future.get());

        CompletableFuture<Object> future3 = CompletableFuture.supplyAsync(new Supplier<Object>() {
            @Override
            public Object get() {
                return null;
            }
        });
    }
}
