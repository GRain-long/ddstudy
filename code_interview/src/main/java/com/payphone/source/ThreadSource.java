package com.payphone.source;

public class ThreadSource {

    static class Demo implements Runnable {

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Demo());
        thread.isInterrupted();
        Thread.interrupted();
    }

}
