package com.payphone.thread;

public class Volatile {
    volatile static int num = 100;

    public static void main(String[] args) {
        num++;
        System.out.println(num);
    }
}
