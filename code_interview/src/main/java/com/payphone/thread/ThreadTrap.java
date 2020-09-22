package com.payphone.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程锁的一些陷阱。
 * 此种写法 线程不安全。
 * 理由如下：
 * Integer是final修饰的,Integer中的值改变后，Integer的引用就变了。所以syn锁的并不是同一个对象！String也是这样。
 * 其他的包装类也是！final修饰的都要注意！
 * 这块问的细致的话，需要用到JMM。
 *
 * 举例说明：
 *  A B两个线程
 *  A 拿到了 Integer = 100的那个锁（对象）。
 *  B 没有拿到 Integer = 100的那个锁（对象）。
 *  A 把Integer变成了99，此时Integer的引用变了！ A
 *  B 想拿到Integer持有的引用，此时那个引用被改了，且无人拥有。（A拿的是100的引用，）
 */
public class ThreadTrap {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread th1 = new Thread(threadDemo, "窗口一");
        Thread th2 = new Thread(threadDemo, "窗口二");
        Thread th3 = new Thread(threadDemo, "窗口三");
        Thread th4 = new Thread(threadDemo, "窗口四");
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }

    static class ThreadDemo implements Runnable {

        // 经典卖票程序
        private Integer tickets = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (tickets) {
                    try {
                        // 休眠一秒
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (tickets == 0) {
                        System.out.println("票卖完了，别来了。");
                        return;
                    }
                    System.out.println(tickets.hashCode());
                    System.out.println(Thread.currentThread().getName() + "：卖了一张，还剩" + --tickets);
                }
            }

        }
    }
}
