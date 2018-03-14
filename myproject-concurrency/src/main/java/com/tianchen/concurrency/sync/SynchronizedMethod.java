package com.tianchen.concurrency.sync;


/**
 * 
 * @author DELL
 * syncFunc为静态变量，func1为实例同步块，所以多个线程执行时，
 * func1只会顺序调用，每次只能有一个线程执行
 */
public class SynchronizedMethod {
	
	public static synchronized void func1() {
		System.out.println(Thread.currentThread().getName() + " is running");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is stop");
	}

	public static void main(String[] args) {

		NewThread newThread1 = new NewThread();
		NewThread newThread2 = new NewThread();
		NewThread newThread3 = new NewThread();

		newThread1.start();
		newThread2.start();
		newThread3.start();

	}
}

class NewThread extends Thread {

	SynchronizedMethod syncFunc = new SynchronizedMethod();

	@Override
	public void run() {
		syncFunc.func1();
	}

}
