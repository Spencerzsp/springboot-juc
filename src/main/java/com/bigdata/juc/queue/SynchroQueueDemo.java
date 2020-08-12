package com.bigdata.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 11:19
 *
 * 同步队列：1.没有容量
 *          2.队列中放入一个元素，必须要取出之后才能重新放入
 */
public class SynchroQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 同步队列
        BlockingQueue<Object> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                synchronousQueue.put("1 ");
                System.out.println(Thread.currentThread().getName() + "put 2");
                synchronousQueue.put("2 ");
                System.out.println(Thread.currentThread().getName() + "put 3");
                synchronousQueue.put("3 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "取出---" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "取出---" + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "取出---" + synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
