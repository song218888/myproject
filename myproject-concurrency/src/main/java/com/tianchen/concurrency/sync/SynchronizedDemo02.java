package com.tianchen.concurrency.sync;

/**
 * 
 * @author DELL
 * 1：保证操作的原子性
 * 2：保证内存的可见性
 */
public class SynchronizedDemo02 implements Runnable{
    //共享资源(临界资源)
    static int i=0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for(int j=0;j<1000;j++){
            increase();
        }
    }
    public static void main(String[] args) throws InterruptedException {
    	SynchronizedDemo02 instance=new SynchronizedDemo02();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
    /**
     * 输出结果:
     * 2000000
     */
}


