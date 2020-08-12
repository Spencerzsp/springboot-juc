package com.bigdata.juc.single;

import java.lang.reflect.Constructor;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 17:28
 */
public enum  EnumDemo {

    INSTANCE;

    public static EnumDemo getInstance(){
        return INSTANCE;
    }
}

class EnumSinle {
    public static void main(String[] args) throws Exception {
        EnumDemo instance = EnumDemo.getInstance();
//        EnumDemo instance2 = EnumDemo.getInstance();

        // 使用反射不能破坏枚举:Cannot reflectively create enum objects
        Constructor<EnumDemo> declaredConstructor = EnumDemo.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumDemo instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
