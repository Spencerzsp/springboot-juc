package com.bigdata.juc.function;

import java.nio.file.DirectoryStream;
import java.util.function.Predicate;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 14:06
 *
 * 断定型接口：有一个输入参数，返回值只能是bool值
 */
public class PredicateDemo {

    public static void main(String[] args) {

        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };

        Predicate<String> predicate = (String s) -> { return s.isEmpty(); };

        System.out.println(predicate.test("a"));
    }
}
