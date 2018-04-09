package com.tianchen.concurrency.thread;

class PrintThread implements Runnable {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		printNumber(name);
//		Thread.yield();
	}
	
	public void printNumber(String threadName) {
		int i = 0;
		while (i++ < 3) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadName + " print:" + i);
		}
	}
}

public class ThreadCommunicationDemo01 {

	public static void main(String[] args) {
		PrintThread printThreadA = new PrintThread();
		printThreadA.setName("A");
		new Thread(printThreadA).start();
		
		PrintThread printThreadB = new PrintThread();
		printThreadB.setName("B");
		new Thread(printThreadB).start();
	}

}
