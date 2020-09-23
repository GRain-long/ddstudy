package com.payphone.thread;

import java.util.Vector;

public class Synchronized {
    static int i = 100;

    public static void main(String[] args) {
        Vector<Object> objects = new Vector<>();

        Object o = new Object();
        synchronized (o) {
            while (true) {
                if (i >= 50) {
                    i--;
                } else {
                    return;
                }
            }
        }
    }
}
