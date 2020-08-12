package com.bigdata.juc.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 14:32
 */
public class StreamDemo {

    public static void main(String[] args) {
        User user1 = new User(1, "a", 20);
        User user2 = new User(2, "b", 23);
        User user3 = new User(3, "c", 25);
        User user4 = new User(4, "d", 28);
        User user5 = new User(5, "e", 30);

        //将用户添加到集合
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        users.stream()
                .filter((user) -> { return user.getId() % 2 != 0;})
                .filter((user) -> {return user.getAge() > 23;})
                .map((user) -> {return user.getName().toUpperCase();})
                .sorted((u1, u2) -> {return u2.compareTo(u1);})
//                .forEach(System.out::println);
//        .forEach(System.out::print);
        .forEach(result -> System.out.println(result));
    }
}
