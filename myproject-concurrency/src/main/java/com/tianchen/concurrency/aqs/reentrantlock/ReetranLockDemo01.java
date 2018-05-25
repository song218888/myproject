package com.tianchen.concurrency.aqs.reentrantlock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReetranLockDemo01 {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();

	public static void main(String[] args) {
		final ReetranLockDemo01 reetranLockDemo01 = new ReetranLockDemo01();

		new Thread() {
			public void run() {
				reetranLockDemo01.insert(Thread.currentThread());
			};
		}.start();
		
		new Thread() {
			public void run() {
				reetranLockDemo01.insert(Thread.currentThread());
			};
		}.start();
	}

	public void insert(Thread thread) {
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			System.out.println(thread.getName() + " 得到了锁");
			for (int i = 0; i < 5; i++) {
				arrayList.add(i);
			}
		} catch (Exception e) {
		} finally {
			System.out.println(thread.getName()+" 释放了锁");
			lock.unlock();
		}
	}

}
