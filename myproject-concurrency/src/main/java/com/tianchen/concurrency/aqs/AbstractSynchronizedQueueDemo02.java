package com.tianchen.concurrency.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 基于AQS实现的互斥锁
 * 
 * @author DELL
 *
 */
public class AbstractSynchronizedQueueDemo02 extends AbstractQueuedSynchronizer {
	private static final long serialVersionUID = 8975239854096554653L;

	public AbstractSynchronizedQueueDemo02() {
	}

	@Override
	protected boolean tryAcquire(int arg) {
		if (compareAndSetState(0, 1)) {
			setExclusiveOwnerThread(Thread.currentThread());
			return true;
		}
		return false;
	}

	@Override
	protected boolean tryRelease(int arg) {
		setExclusiveOwnerThread(null);
		setState(0);
		return true;
	}

	public void lock() {
		acquire(1);
	}

	public boolean tryLock() {
		return tryAcquire(1);
	}

	public void unlock() {
		release(1);
	}

	public boolean isLocked() {
		return isHeldExclusively();
	}

	public static void main(String[] args) throws InterruptedException {
		final AbstractSynchronizedQueueDemo02 lock = new AbstractSynchronizedQueueDemo02();
		lock.lock();

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					System.out.println(Thread.currentThread().getId() + " acquired the lock!");
					lock.unlock();
				}
			}).start();
			// 简单的让线程按照for循环的顺序阻塞在lock上
			Thread.sleep(100);
		}

		System.out.println("main thread unlock!");
		lock.unlock();
	}
}
