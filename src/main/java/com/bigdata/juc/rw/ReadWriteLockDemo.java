package com.bigdata.juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 9:51
 *
 * ReadWriteLock:读写锁，写的时候只能有一条线程，读的时候随意并发
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

//        MyCache myCache = new MyCache();
        MyCacheLock myCacheLock = new MyCacheLock();

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp + "", temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp + "");
            }, String.valueOf(i)).start();
        }

    }
}

/**
 * 加读写锁的缓存
 */
class MyCacheLock{

    private volatile Map<String, Object> map = new HashMap<>();

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 写
    public void put(String key, Object value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    // 读
    public void get(String key){

        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "开始读取");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}


/**
 * 自定义缓存
 */
class MyCache{

    private volatile Map<String, Object> map = new HashMap<>();

    // 写
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName() + "开始写入");
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }

    // 读
    public void get(String key){
        System.out.println(Thread.currentThread().getName() + "开始读取");
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成");
    }

}
