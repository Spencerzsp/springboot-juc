package com.bigdata.juc.unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 15:25
 */
public class ListTest {

    public static void main(String[] args) {

        // ArrayList:在多线程的情况下不安全，报错java.util.ConcurrentModificationException
        // List<String> list = new ArrayList<>();

        // 解决方案
        // 1.普通解决方案
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 2.JUC解决方案,CopyOnWrit:写入时复制
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
