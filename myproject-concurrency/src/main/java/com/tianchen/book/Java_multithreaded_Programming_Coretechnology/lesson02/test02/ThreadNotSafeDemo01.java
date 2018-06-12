package com.tianchen.book.Java_multithreaded_Programming_Coretechnology.lesson02.test02;

class PublicVar {

	public String username = "A";
	public String password = "AA";

	synchronized public void setValue(String username, String password) {
		try {
			this.username = username;
			Thread.sleep(5000);
			this.password = password;

			System.out.println("setValue method thread name=" + Thread.currentThread().getName() + " username="
					+ username + " password=" + password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getValue() {
		System.out.println("getValue method thread name=" + Thread.currentThread().getName() + " username=" + username
				+ " password=" + password);
	}
}

class ThreadA extends Thread {

	private PublicVar publicVar;

	public ThreadA(PublicVar publicVar) {
		super();
		this.publicVar = publicVar;
	}

	@Override
	public void run() {
		super.run();
		publicVar.setValue("B", "BB");
	}
}

public class ThreadNotSafeDemo01 {
	public static void main(String[] args) {
		try {
			PublicVar publicVarRef = new PublicVar();
			ThreadA thread = new ThreadA(publicVarRef);
			thread.start();

			Thread.sleep(100);// 打印结果受此值大小影响

			publicVarRef.getValue();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
