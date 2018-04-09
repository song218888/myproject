package com.tianchen.concurrency.thread;

class ThreadTest implements Runnable {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.getName() + "------" + i);
		}
	}

}

public class ThreadJoinDemo01 {
	public static void main(String[] args) throws Exception {
		ThreadTest test = new ThreadTest();
		ThreadTest test2 = new ThreadTest();
		test.setName("one");
		test2.setName("two");
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test2);
		t1.start();
		t2.start();

		/**
		 * 主线程向下转时，碰到了t1.join(),t1要申请加入到运行中来，就是要CPU执行权。
		 * 这时候CPU执行权在主线程手里，主线程就把CPU执行权给放开，陷入冻结状态。
		 * 活着的只有t1了，只有当t1拿着执行权把这些数据都打印完了，主线程才恢复到运行中来
		 */
		// join方法确保t1执行之后执行t2
		t1.join();
		
	}
}
