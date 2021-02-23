package com.bigdata.juc.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 16:53
 *
 * 信号量
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        // 设置线程数量：5个车位
        Semaphore semaphore = new Semaphore(5);

        for (int i = 1; i <= 5 ; i++) {
            new Thread(() -> {
                try {
                    // 获取
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
