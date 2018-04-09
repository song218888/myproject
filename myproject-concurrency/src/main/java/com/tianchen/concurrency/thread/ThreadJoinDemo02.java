package com.tianchen.concurrency.thread;

class ThreadA extends Thread{
	public ThreadA() {
		super("ThreadA");
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
		}
	}
}

class ThreadB extends Thread{
	public ThreadB() {
		super("ThreadB");
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
		}
	}
}

public class ThreadJoinDemo02 {
	public static void main(String[] args)throws Exception {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		threadA.start();
		threadB.start();
		threadB.join();
	}
}
