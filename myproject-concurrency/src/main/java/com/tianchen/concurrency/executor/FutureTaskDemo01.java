package com.tianchen.concurrency.executor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * FutureTask与Future区别
 * 
 * 它有一个回调函数protected void done()
 * 
 * 当任务结束时，该回调函数会被触发
 * 
 * 因此，只需重载该函数，即可实现在线程刚结束时就做一些事情
 * 
 * @author DELL
 *
 */
public class FutureTaskDemo01 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();  
        for(int i=0; i<5; i++) {  
            Callable<String> c = new Task();  
            MyFutureTask ft = new MyFutureTask(c);  
            executor.submit(ft);  
        }  
        executor.shutdown();  
	}
}


class MyFutureTask extends FutureTask<String> {  
	  
    public MyFutureTask(Callable<String> callable) {  
        super(callable);  
    }  
  
    @Override  
    protected void done() {  
        try {  
            System.out.println(get() + " 线程执行完毕！~");  
        } catch (InterruptedException | ExecutionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
      
      
}  
  
class Task implements Callable<String> {  
  
    @Override  
    public String call() throws Exception {  
        Random rand = new Random();  
        TimeUnit.SECONDS.sleep(rand.nextInt(12));  
        return Thread.currentThread().getName();  
    }  
}  
