package com.tianchen.concurrency.threadlocal;

public class ThreadLocalDemo04 {
	private static ThreadLocal<Integer> firstNum = new ThreadLocal<Integer>();
	
	private static ThreadLocal<Integer> secondNum = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 21;
		};
	};
	
	private static ThreadLocal<Integer> thirdNum = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 31;
		};
	};
	
	public static void main(String[] args) {
		System.out.println(firstNum.get());
		firstNum.set(11);
		System.out.println(firstNum.get());
		System.out.println(secondNum.get());
		System.out.println(thirdNum.get());
	}
}
