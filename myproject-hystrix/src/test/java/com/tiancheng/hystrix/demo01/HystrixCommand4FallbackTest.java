package com.tiancheng.hystrix.demo01;

import org.junit.Test;

import com.tiancheng.hystrix.demo01.HystrixCommand4Fallback;

public class HystrixCommand4FallbackTest {
	@Test
	public void testExecute() {
		try {
			System.out.println(new HystrixCommand4Fallback("Hlx").execute());
		} catch (Exception e) {
			System.out.println("run()抛出HystrixBadRequestException时，会被捕获到这里" + e.getCause());
		}
	}
}
