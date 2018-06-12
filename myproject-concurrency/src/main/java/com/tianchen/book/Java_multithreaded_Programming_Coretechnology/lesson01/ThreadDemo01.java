package com.tianchen.book.Java_multithreaded_Programming_Coretechnology.lesson01;

class MyThread extends Thread{
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("run=" + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ThreadDemo01 {
	public static void main(String[] args) {
		try {

			MyThread thread = new MyThread();
			thread.setName("myThread");
			thread.start();

			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("main=" + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
