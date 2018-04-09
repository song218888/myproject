package com.tianchen.concurrency.thread;

class YieldThread extends Thread {
	
	 public YieldThread(String name) {  
	        super(name);  
	    }  

	@Override
	public void run() {
		for (int i = 1; i <= 50000; i++) {  
            System.out.println("" + this.getName() + "-----" + i);  
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）  
            if (i == 30) {  
                yield();  
            }  
        }  

	}
}

public class ThreadYieldDemo01 {
	public static void main(String[] args) {
		new YieldThread("t1").start();
		new YieldThread("t2").start();

	}
}
