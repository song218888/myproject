package com.tiancheng.gc;

/**
 * 主要用来分析线程
 * 
 * @author dell
 *
 */
public class JstackDemo01 {
	public static void main(String[] args) {
		Thread thread = new Thread(new Worker());
		thread.start();
	}

	static class Worker implements Runnable {
		@Override
		public void run() {
			while (true) {
				System.out.println("Thread Name:" + Thread.currentThread().getName());
			}
		}
	}
}
