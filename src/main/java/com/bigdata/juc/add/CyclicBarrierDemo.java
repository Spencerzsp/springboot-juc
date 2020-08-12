package com.bigdata.juc.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 16:36
 *
 * 加法计数器
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("成功~");
        });

        for (int i = 1; i <= 7; i++) {
            // lambda表达式中不能使用外部变量i，必须声明为final
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集" + temp + "颗龙珠");

                try {
                    // 等待计数器达到主线程锁规定的数量，然后执行主线程的逻辑
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
