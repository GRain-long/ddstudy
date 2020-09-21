package com.payphone.source;

public class IntegerSource {
    public static void main(String[] args) {
        /**
         * Integer 经典面试题
         */
        Integer a = 10000;
        Integer b = new Integer(10000);
        System.out.println(a == b); // 对象和基本类型比较 false

        Integer c = new Integer(100);
        Integer d = new Integer(100);
        System.out.println(c == d); // 两个对象比较 false

        Integer e = 100;
        Integer f = 100;
        System.out.println(e == f); // true 都是同一个缓存 字节码编译后 e = Integer.valueOf(100)  valueOf会先看缓存（IntegerCache中有没有） 緩存的数值范围为-128~127

        Integer g = 1000;
        Integer h = 1000;
        System.out.println(g == h);// false 字节码编译后 e = Integer.valueOf(100)  valueOf会先看缓存（IntegerCache中有没有） 緩存的数值范围为-128~127

    }
}
