package com.bigdata.juc.pool;

import java.util.concurrent.*;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 11:47
 *
 * Executors：三大方法
 * 使用了线程池后，需要是要线程池来创建线程
 *
 * 拒绝策略：1.ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，不会处理这个人的业务，会抛出异常
 *          2.ThreadPoolExecutor.CallerRunsPolicy() //使用main线程进行处理
 *          3.ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常
 *          4.ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常
 */
public class Demo01 {

    public static void main(String[] args) {

        //单个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建大小为5的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //可伸缩大小的线程池
//        Executors.newCachedThreadPool();

        //工作中一般使用ThreadPoolExecutor
        //最大线程到底该如何定义
        // 1.CPU 密集型：几核就是最大允许同时执行几个线程
        // 2.IO 密集型：判断程序中十分耗IO的线程任务，只要大于这些数量就行，一般大于2倍

        // 获取CPU核数
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                processors,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，不会处理这个人的业务，会抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy() //哪里来的去哪里
//                new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常
        );

        try {
            // 最大承载：queue + max
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }

    }
}
