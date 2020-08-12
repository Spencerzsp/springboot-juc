package com.bigdata.juc.function;

import java.util.function.Supplier;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 14:18
 *
 * Supplier：供给型接口，没有输入参数，只有返回值
 */
public class SuplyDemo {

    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 1024;
//            }
//        };

        Supplier<Integer> supplier = () -> {return 1024;};

        System.out.println(supplier.get());
    }
}
