package com.tianchen.concurrency.threadlocal;

public class ThreadStaticDemo01 implements Runnable{
	
	public static int num = 0;
	
	@Override
	public void run() {
		num = 3;
		System.out.println("当前线程是： " + Thread.currentThread().getName()+" , num = " + num);
		num = 5;
		System.out.println("当前线程是： " + Thread.currentThread().getName()+" , num*2 = " + num*2);
	}
	
	public static void main(String[] args) {
		ThreadStaticDemo01 t = new ThreadStaticDemo01();
		for (int i = 0; i < 10; i++) {
			new Thread(t,"Thread-"+i).start();
		}
	}
}
