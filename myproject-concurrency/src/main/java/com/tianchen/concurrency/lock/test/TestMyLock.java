package com.tianchen.concurrency.lock.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 自己动手实现锁 参考：http://www.cnblogs.com/qingquanzi/p/8158484.html
 * 
 * @author DELL
 *
 */
public class TestMyLock {
	private static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					add(i);
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				print();
			}
		});
		t1.start();
		t2.start();
	}

	private synchronized static void add(int i) {
		list.add(i);
	}

	private synchronized static void print() {
		Iterator<Integer> iterator = list.iterator();
		System.out.println(list.size()+"=============");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
