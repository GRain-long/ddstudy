package com.payphone.thread;

import java.util.concurrent.TimeUnit;

/**
 * 读写操作的原子性
 */
public class Atomic {
    private static boolean stopRequested = false;


    public static void demo1() throws InterruptedException {
        // lambda表达式
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                }
            }
        });

        thread.start();
        System.out.println("**********");
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        System.out.println("**********");
        TimeUnit.SECONDS.sleep(1);

    }


    public static void main(String[] args) throws InterruptedException {
        demo1();
    }
}
