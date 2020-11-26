package com.bigdata.juc.function;

import java.util.function.Consumer;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 14:12
 *
 * Consumer：消费型接口，只有输入，没有返回值
 */
public class ConsumerDemo {

    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("haha");
    }
}
