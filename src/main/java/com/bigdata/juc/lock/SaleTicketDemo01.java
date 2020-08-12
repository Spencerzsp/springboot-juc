package com.bigdata.juc.lock;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 13:16
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(() -> {
            ticket.sale();
        }, "A").start();

        new Thread(() -> {
            ticket.sale();
        }, "B").start();

        new Thread(() -> {
            ticket.sale();
        }, "C").start();
    }
}

class Ticket{

    private int number = 50;

    public void sale(){
        System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "张票");
    }
}