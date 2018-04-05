package com.tianchen.concurrency.thread.initialize;


class RunnableThread implements Runnable{
	
	private String threadName;
	
	public RunnableThread(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		System.out.println("RunnableThead : " + Thread.currentThread().getName() + " === " + threadName);
		
	}
}


public class RunnableDemo01 {
	public static void main(String[] args) {
		Thread thread = new Thread(new RunnableThread("aaaa"));
		thread.setName("runnablethreadName");
		thread.start();
	}
}
