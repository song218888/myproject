package com.tiancheng.hystrix.demo01.test;

import org.junit.Test;

import com.tiancheng.hystrix.demo01.HelloWorldHystrixFallback;

public class HystrixFallbackTest {
	@Test
	public void testExecute() {
		try {
			System.out.println(new HelloWorldHystrixFallback("Hlx").execute());
		} catch (Exception e) {
			System.out.println("run()抛出HystrixBadRequestException时，会被捕获到这里" + e.getCause());
		}
	}
}
