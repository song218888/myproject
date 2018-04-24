package com.tianchen.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 两个线程用一个对象
 * @author dell
 *
 */
public class LockInterruptiblyDemo01 {
	private Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		LockInterruptiblyDemo01 lockInterruptiblyDemo01 = new LockInterruptiblyDemo01();
		MyThread thread1 = new MyThread(lockInterruptiblyDemo01);
        MyThread thread2 = new MyThread(lockInterruptiblyDemo01);
        thread1.start();
        thread2.start();
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }  
 
    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {  
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }  
    }
}
 
class MyThread extends Thread {
    private LockInterruptiblyDemo01 test = null;
    public MyThread(LockInterruptiblyDemo01 test) {
        this.test = test;
    }
    @Override
    public void run() {
 
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}