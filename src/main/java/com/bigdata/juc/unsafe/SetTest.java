package com.bigdata.juc.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 15:52
 */
public class SetTest {

    public static void main(String[] args) {

        // 多线程时不安全
        // Set<String> set = new HashSet<>();

        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(set);
            }).start();
        }
    }
}
