package com.tiancheng.createpattern.factory;

interface IProduct {
	void print();
}

abstract class AbstractProduct implements IProduct {
	protected void printBefore() {
		System.out.println("before print"); // 这里所公共的实现
	}
}

class AProduct extends AbstractProduct {
	private String name;

	public AProduct(String name) {
		this.name = name;
	}

	@Override
	public void print() {
		this.printBefore();
		System.out.println("print A >>>" + name);
	}
}

class BProduct extends AbstractProduct {
	private String name;

	public BProduct(String name) {
		this.name = name;
	}

	@Override
	public void print() {
		this.printBefore();
		System.out.println("print B >>>" + name);
	}
}

public class SimpleFactory {
	public static IProduct getProduct(String name) {
		if ("A".equals(name)) {
			return new AProduct(name);
		} else if ("B".equals(name)) {
			return new BProduct(name);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
