package com.tianchen.concurrency.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * @author dell
 *
 */
public class SpinLockDemo01 {
	private AtomicReference<Thread> owner = new AtomicReference<Thread>();

	public void lock() {
		Thread currentThread = Thread.currentThread();

		// 如果锁未被占用，则设置当前线程为锁的拥有者
		while (!owner.compareAndSet(null, currentThread)) {
		}
	}

	public void unlock() {
		Thread currentThread = Thread.currentThread();

		// 只有锁的拥有者才能释放锁
		owner.compareAndSet(currentThread, null);
	}

}
