package com.bigdata.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 13:16
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {

        Ticket2 ticket = new Ticket2();

        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "A").start();

        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "B").start();

        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); }, "C").start();
    }
}

class Ticket2{

    private int number = 50;

    Lock lock = new ReentrantLock();
    public void sale(){

        lock.lock();


        // ctl + alt + t：快捷键try-catch-finally
        try {
            while (number > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票,剩余" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}