package com.tianchen.book.Java_multithreaded_Programming_Coretechnology.lesson02.test01;

class HasSelfPrivateNum {
	int num = 0;
	
	public void addI(String username) {
		try {
			if (username.equals("a")) {
				num = 100;
				System.out.println("a set over!");
				Thread.sleep(2000);
			} else {
				num = 200;
				System.out.println("b set over!");
			}
			System.out.println(username + " num=" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class ThreadA extends Thread {
	private HasSelfPrivateNum numRef;
	public ThreadA(HasSelfPrivateNum numRef) {
		this.numRef = numRef;
	}

	@Override
	public void run() {
		numRef.addI("a");
	}
}

class ThreadB extends Thread {
	private HasSelfPrivateNum numRef;
	public ThreadB(HasSelfPrivateNum numRef) {
		this.numRef = numRef;
	}

	@Override
	public void run() {
		super.run();
		numRef.addI("b");
	}
}


public class ThreadSafeDemo01 {
	public static void main(String[] args) {
		HasSelfPrivateNum numRef1 = new HasSelfPrivateNum();
		HasSelfPrivateNum numRef2 = new HasSelfPrivateNum();

		ThreadA athread = new ThreadA(numRef1);
		athread.start();

		ThreadB bthread = new ThreadB(numRef2);
		bthread.start();

	}
}
