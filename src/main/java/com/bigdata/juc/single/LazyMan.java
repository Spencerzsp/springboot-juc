package com.bigdata.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 16:36
 */
public class LazyMan {

    private static boolean spencer_spark = false;

    // 单例模式，构造器私有
    private LazyMan(){
//        System.out.println(Thread.currentThread().getName());
        synchronized (LazyMan.class){
            if (spencer_spark == false){
                spencer_spark = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常！");
            }
        }
    }

    // 双重检测锁模式 DCL懒汉式单例,必须添加volatile
    private volatile static LazyMan lazyMan;
    public static LazyMan getInstance(){
        if (lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan(); //不是一个原子构造
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把对象指向这个空间
                     */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                lazyMan.getInstance();
//            }).start();
//        }

        // 使用反射破坏单例模式
//        LazyMan instance = LazyMan.getInstance();

        // 使用反射获取标志位进而破坏单例
        Field field = LazyMan.class.getDeclaredField("spencer_spark");
        field.setAccessible(true);

        Constructor<LazyMan> lazyManConstructor = LazyMan.class.getDeclaredConstructor(null);
        lazyManConstructor.setAccessible(true);
        LazyMan instance = lazyManConstructor.newInstance();

        // 将构造器中第一次创建对象修改的标志位spencer_spark重新改为false
        field.set(instance, false);

        LazyMan instance2 = lazyManConstructor.newInstance();

        System.out.println(instance); //com.bigdata.juc.single.LazyMan@2f0e140b
        System.out.println(instance2);//com.bigdata.juc.single.LazyMan@2f0e140b

    }
}
