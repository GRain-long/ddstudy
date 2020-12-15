package com.payphone.thread;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * 创建线程的方式：
 * 1. 继承Thread
 * 2. 实现Runnable接口
 * 3. 实现Callable接口，创建FutureTask类。
 * FutureTask<>(new Callable(){
 * xxxx
 * });
 * <p>
 * 创建四种线程池的方式
 */
public class CreateThread {
    static int count = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CreateThread.thread2();
    }

    // 实现Runnable接口，继承那个就不写了
    public static void thread1() {
        Thread thread = new Thread(() -> {
            System.out.println("thread1");
        });
        thread.start();
    }

    public static void thread2() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            public String call() throws InterruptedException {
                TimeUnit.SECONDS.sleep(5);
                return "!23";
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        // 会拿到线程执行完成后返回的结果
        String s = futureTask.get();
        System.out.println(s);
    }


    public static void pool1() {
        Logger logger = Logger.getLogger("123");
        logger.info("nihaoya");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello 1");
            }
        });
        cachedThreadPool.shutdown();
    }

    // 指定最大线程数，超过的会在队列中等待
    public static void pool2() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 11; i++) {
            fixedThreadPool.execute(() -> {
                count++;
                System.out.println("1");
            });
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println(count);
        fixedThreadPool.shutdown();
    }

    // 定时执行
    public static void pool3() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("start create thread");
        scheduledThreadPool.schedule(() -> {
            System.out.println("11");
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.shutdown();
    }

    public static void pool4() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            System.out.println("pool4");
        });
        executorService.shutdown();
    }

}
