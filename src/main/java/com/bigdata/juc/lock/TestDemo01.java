package com.bigdata.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 11:51
 */
public class TestDemo01 {
    public static void main(String[] args) throws InterruptedException {

        // 查看cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        new Thread().start();

        // 线程有6中状态
//        Thread.State;

        // wait与sleep的区别
        // wait -- Object
        // sleep -- Thread
        TimeUnit.DAYS.sleep(1);
        TimeUnit.SECONDS.sleep(2);
        Thread.sleep(1);
    }
}
