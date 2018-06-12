package com.tianchen.concurrency.threadlocal;

import java.util.Random;

public class ThreadLocalDemo01 {
    public static class MyRunnable1 implements Runnable {
    	private ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>();
        private ThreadLocal<Integer> threadlocal2 = new ThreadLocal<Integer>();
        @Override
        public void run() {
        	threadlocal.set(new Random().nextInt(10));
            threadlocal2.set(2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " : " + threadlocal.get());
            System.out.println(Thread.currentThread() + " : " + threadlocal2.get());
        }
    }
 
    public static void main(String[] args) {
        System.out.println("start");
        MyRunnable1 runnable = new MyRunnable1();
        Thread thread1 = new Thread(runnable);
//        Thread thread2 = new Thread(runnable);
        thread1.start();
//        thread2.start();
    }
}
