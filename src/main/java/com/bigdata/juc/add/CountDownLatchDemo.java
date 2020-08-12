package com.bigdata.juc.add;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 16:24
 *
 * 计算器
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Lock lock = new ReentrantLock();

        for (int i = 1; i <= 5 ; i++) {
            lock.lock();
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go out!");
                countDownLatch.countDown(); //-1
            }, String.valueOf(i)).start();
        }

        // 等待计数器归0，然后再向下执行
        countDownLatch.await();

        System.out.println("Close door");
    }
}
