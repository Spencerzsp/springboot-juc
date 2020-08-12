package com.bigdata.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 14:11
 *
 * 生产者与消费者通信:使用Lock和Condition实现，按照指定顺序通知和唤醒线程执行
 */
public class ProducerAndConsumerDemo03 {

    public static void main(String[] args) {

        Data3 data3 = new Data3();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data3.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}

class Data3{

    Lock lock = new ReentrantLock();

    // 替换synchronize中的wait和notify
    // 设置多个监视器
    Condition condition1 = lock.newCondition();    // 1A
    Condition condition2 = lock.newCondition();    // 2B
    Condition condition3 = lock.newCondition();    // 3C

    // 设置标志位
    int number = 1;

    public void printA() throws InterruptedException {

        lock.lock();

        try {
            // 业务代码
            // if (number != 0):容易导致虚假唤醒，需要使用while
            while (number != 1){
                condition1.await();
            }
            number = 2;
            System.out.println(Thread.currentThread().getName() + "=>AAAAA");

            // 通知，唤醒指定的人
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB() throws InterruptedException {

        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            number = 3;
            System.out.println(Thread.currentThread().getName() + "=>BBBBB" );

            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {

        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            number = 1;
            System.out.println(Thread.currentThread().getName() + "=>CCCCC" );

            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
