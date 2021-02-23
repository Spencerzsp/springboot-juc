package com.bigdata.juc.test;

import java.util.concurrent.CompletableFuture;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/14 11:31
 */
public class TestDemo {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            final int temp = i;
//            new Thread(() -> {
////                System.out.println(Thread.currentThread().getName());
//                CompletableFuture.runAsync(() -> {
////                    System.out.println(temp + Thread.currentThread().getName());
//                    System.out.println("CompletableFuture.runAsync的使用！");
//                });
//            }, String.valueOf(i)).start();
//        }

//        System.out.println(1 << 3);

        Thread thread1 = new Thread(() -> {
            System.out.println("线程1");
        }, "haha");
        thread1.start();

        System.out.println(thread1.getName());
    }
}
