package com.tianchen.book.Java_multithreaded_Programming_Coretechnology.lesson02.test04;


class Service {

	private String anyString = new String();

	public void a() {
		try {
			synchronized (anyString) {
				System.out.println("a begin");
				Thread.sleep(3000);
				System.out.println("a   end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public void b() {
		System.out.println("b begin");
		System.out.println("b   end");
	}
}


class ThreadA extends Thread {
	private Service service;
	public ThreadA(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.a();
	}
}


class ThreadB extends Thread {
	private Service service;
	public ThreadB(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.b();

	}
}



public class SynBlockThreadDemo01 {
	public static void main(String[] args) {
		Service service = new Service();

		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();

	}

}
