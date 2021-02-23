package com.bigdata.juc.function;

import java.util.function.Function;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 13:44
 *
 * 函数式接口：1.有一个输入参数，一个输出
 *            2.只要是函数式接口，就可以使用lambda表达式简化
 */
public class FunctionDemo {

    public static void main(String[] args) {

//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        Function<String, Object> function = s -> s + "_lambda";
        Function<String, String> thenFuntion = function.andThen(str -> str + "_haha");

        System.out.println(thenFuntion.apply("abc"));
    }
}
