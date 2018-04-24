package com.tianchen.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo01 {
	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		
		try {
			lock.tryLock(500, TimeUnit.MICROSECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}
