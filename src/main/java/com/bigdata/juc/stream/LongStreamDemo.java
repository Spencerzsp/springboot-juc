package com.bigdata.juc.stream;

import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 15:11
 */
public class LongStreamDemo {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (int i = 0; i <= 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum: " + sum + "时间：" + (end-start));
    }
    public static void test2(){

        long start = System.currentTimeMillis();
        OptionalLong sumed = LongStream.rangeClosed(0, 10_0000_0000).parallel().reduce((a, b) -> {
            return a + b;
        });

        long end = System.currentTimeMillis();

        System.out.println("sum: " + sumed + "时间：" + (end-start));
    }
}
