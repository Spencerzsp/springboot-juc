package com.bigdata.juc.single;

/**
 * @ description:
 * @ author: spencer
 * @ date: 2020/8/12 16:33
 */
public class Hungery {

    private Hungery(){

    }

    private static Hungery hungery = new Hungery();

    public static void main(String[] args) {
//        hungery
    }

    public static void test(){
        System.out.println("hungry");
    }
}
