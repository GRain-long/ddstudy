package com.payphone.source;

public class ThreadLocalSource {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
//        System.out.println(local.get());

        local.set("hello");
        System.out.println(local.get());

        local.remove();
        System.out.println(local.get());
    }
}
