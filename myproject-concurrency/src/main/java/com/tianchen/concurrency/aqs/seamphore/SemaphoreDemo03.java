package com.tianchen.concurrency.aqs.seamphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo03 {
	public static void main(String[] args)throws Exception {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		final Semaphore semaphore = new Semaphore(3);
		
		for(int i=0;i<20;i++){
			final int num = i;
			Runnable run = new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("Accessing: " + num);
						Thread.sleep((long)(Math.random() * 3000));
						semaphore.release();
						System.out.println("******" + semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			executors.execute(run);
		}
		
		executors.shutdown();
	}
}
