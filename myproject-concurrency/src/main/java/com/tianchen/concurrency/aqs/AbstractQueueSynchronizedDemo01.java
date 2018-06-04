package com.tianchen.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AbstractQueueSynchronizedDemo01 {
	
	public static void main(String[] args) throws Exception{
		AbstractQueuedSynchronizer aqs ;
		
		Object object;
		
		Lock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
		
		
		CountDownLatch cdl = new CountDownLatch(5);
		cdl.await();
		cdl.countDown();
		
		
		Semaphore semaphore = new Semaphore(10);
		semaphore.acquire();
		semaphore.release();
		
		
		CyclicBarrier cb = new CyclicBarrier(10);
		cb.await();
		
	
		ReentrantReadWriteLock rrwl;
	}

}
