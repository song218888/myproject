package com.tianchen.concurrency.thread.initialize;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor   接口
 * @author Administrator
 *
 */


class CallableThread implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		return 5;
	}
}


public class ExecutorsDemo01 {
	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Thread() {
			@Override
			public void run() {
				System.out.println("执行器： executors。。。。。。。。。");
			}
		});
		
		Future<Integer> future = executorService.submit(new CallableThread());
		System.out.println(future.get());
		
		executorService.shutdown();
	}
}
