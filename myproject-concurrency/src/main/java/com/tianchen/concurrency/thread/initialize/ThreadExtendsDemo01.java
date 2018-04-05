package com.tianchen.concurrency.thread.initialize;

public class ThreadExtendsDemo01 {
	public static void main(String[] args) {
		ExtendsThreadDemo extendsThreadDemo = new ExtendsThreadDemo("extendsThread");
		extendsThreadDemo.start();
	}
}


class ExtendsThreadDemo extends Thread{
	public ExtendsThreadDemo(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void run() {
		System.out.println("ExtendsThreadDemo : " + Thread.currentThread().getName());
	}
}