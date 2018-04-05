package com.tianchen.concurrency.thread.initialize;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class CallableDemo implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {  //实现的call() 有返回值
        int sum = 0;
        for(int i = 0;i<1000000;i++){
            sum+=i;
        }
        return sum;
    }
}



public class CallableDemo01 {
	
	public static void main(String[] args) {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};

		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			System.out.println("=====" + future.get());
//			new Thread(future).start();
//			System.out.println("=====" + future.get());
//			Thread.sleep(2000);// 可能做一些事情
//			System.out.println(future.get());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
	}
}
