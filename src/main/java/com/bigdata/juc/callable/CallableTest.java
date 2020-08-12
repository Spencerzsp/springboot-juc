package com.bigdata.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/11 16:09
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //new Thread(new Runnable).start();
        //new Thread(new FutureTask<>()).start();
        //new Thread(new FutureTask<>(new Callable)).start()

        MyThread thread = new MyThread();
        FutureTask<String> futureTask = new FutureTask<>(thread);

        // 使用callable的启动方式
        new Thread(futureTask).start();

        // 获取callable的返回结果
        System.out.println(futureTask.get());

    }
}

class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call被调用");
        return "12345";
    }
}
