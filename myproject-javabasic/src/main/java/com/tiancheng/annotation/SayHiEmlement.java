package com.tiancheng.annotation;

public class SayHiEmlement {
	// 普通的方法
	public void SayHiDefault(String name) {
		System.out.println("Hi, " + name);
	}

	//使用注解并传入参数的方法
	@SayHiAnnotation(paramValue = "Jack")
	public void SayHiAnnotation(String name) {
		System.out.println("Hi, " + name);
	}

	//使用注解并使用默认参数的方法
	@SayHiAnnotation
	public void SayHiAnnotationDefault(String name) {
		System.out.println("Hi, " + name);
	}
}
