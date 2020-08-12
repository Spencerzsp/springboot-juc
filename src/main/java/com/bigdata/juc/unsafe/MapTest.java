package com.bigdata.juc.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 15:56
 */
public class MapTest {

    public static void main(String[] args) {

        // HashMap多线程下不安全：java.util.ConcurrentModificationException
        // Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,3));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
