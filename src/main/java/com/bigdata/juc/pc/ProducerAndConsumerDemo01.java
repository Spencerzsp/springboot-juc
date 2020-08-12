package com.bigdata.juc.pc;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 14:11
 *
 * 生产者与消费者通信
 */
public class ProducerAndConsumerDemo01 {

    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> { for (int i = 0; i < 10; i++) data.increment(); }, "A").start();

        new Thread(() -> { for (int i = 0; i < 10; i++) data.deincrement(); }, "B").start();

        new Thread(() -> { for (int i = 0; i < 10; i++) data.increment(); }, "C").start();

        new Thread(() -> { for (int i = 0; i < 10; i++) data.deincrement(); }, "D").start();

    }
}

class Data{
    private int number;

    public synchronized void increment(){

        // if (number != 0):容易导致虚假唤醒，需要使用while
        while (number != 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();

    }

    public synchronized void deincrement(){
        while (number == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);

        this.notifyAll();
    }
}
