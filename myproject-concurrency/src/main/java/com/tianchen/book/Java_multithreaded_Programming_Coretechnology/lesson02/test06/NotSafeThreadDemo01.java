package com.tianchen.book.Java_multithreaded_Programming_Coretechnology.lesson02.test06;

import java.util.ArrayList;
import java.util.List;

class MyOneList {

	private List<String> list = new ArrayList<String>();

	synchronized public void add(String data) {
		list.add(data);
	}

	synchronized public int getSize() {
		return list.size();
	}

}

class MyThread1 extends Thread {

	private MyOneList list;

	public MyThread1(MyOneList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "A");
	}

}

class MyThread2 extends Thread {

	private MyOneList list;

	public MyThread2(MyOneList list) {
		super();
		this.list = list;
	}

	@Override
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "B");
	}

}

class MyService {

	public MyOneList addServiceMethod(MyOneList list, String data) {
		try {
//			synchronized (list) {
				if (list.getSize() < 1) {
					Thread.sleep(2000);
					list.add(data);
				}
//			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}

}

public class NotSafeThreadDemo01 {
	public static void main(String[] args) throws InterruptedException {
		MyOneList list = new MyOneList();

		MyThread1 thread1 = new MyThread1(list);
		thread1.setName("A");
		thread1.start();

		MyThread2 thread2 = new MyThread2(list);
		thread2.setName("B");
		thread2.start();
		
		Thread.sleep(6000);
		
		System.out.println("listSize=" + list.getSize());

	}
}
