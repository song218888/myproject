package com.tianchen.concurrency.thread.initialize;

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

class Task implements Callable<String> {
	@Override
	public String call() throws Exception {
		Random random = new Random();
		TimeUnit.SECONDS.sleep(random.nextInt(5));
		return Thread.currentThread().getName();
	}
}

public class FutureDemo01 {

	static ExecutorService executor = Executors.newFixedThreadPool(5);

	public static List<Future<String>> init() {
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			Future<String> future = executor.submit(new Task());
			list.add(future);
		}
		return list;
	}

	public static void isDone(List<Future<String>> list) throws ExecutionException, InterruptedException {
		boolean flag = true;
		while (flag) {

			for (Iterator<Future<String>> iter = list.iterator(); iter.hasNext();) {
				Future<String> f = iter.next();
				if (f.isDone()) {
					System.out.println(f.get());
					iter.remove();
				}
			}
			if (list.size() == 0) {
				flag = false;
			}

		}
	}

	public static void main(String[] args) throws Exception {

		List<Future<String>> list = init();
		isDone(list);
		System.out.println("执行完毕");
		executor.shutdownNow();
	}

}
