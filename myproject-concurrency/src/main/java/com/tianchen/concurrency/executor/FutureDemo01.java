package com.tianchen.concurrency.executor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * 若开启了多个任务，我们无从知晓哪个任务最先结束。因此，
 * 
 * 若要实现“当某任务结束时，立刻做一些事情，例如记录日志”这一功能，就需要写一些额外的代码
 * 
 * @author DELL
 *
 */
public class FutureDemo01 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executor2 = Executors.newFixedThreadPool(5);

		class Task implements Callable<String> {
			@Override
			public String call() throws Exception {

				Random rand = new Random();
				TimeUnit.SECONDS.sleep(rand.nextInt(10));
				return Thread.currentThread().getName();
			}
		}

		List<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			Future<String> f = executor2.submit(new Task());
			results.add(f);
		}

		boolean flag = true;
		while (flag) {

			for (Iterator<Future<String>> iter = results.iterator(); iter.hasNext();) {
				Future<String> f = iter.next();
				if (f.isDone()) {
					System.out.println(f.get());
					iter.remove();

				}
			}
			if (results.size() == 0) {
				flag = false;
			}

		}
		System.out.println("执行完毕");
		executor2.shutdownNow();

	}

}
