package com.tiancheng.hystrix.demo01.test;

import java.io.IOException;

import org.junit.Test;

import com.tiancheng.hystrix.demo01.HystrixCircuitBreaker;

public class HystrixCircuitBreakerTest {
	
  @Test
  public void testSynchronous() throws IOException {
  	for(int i = 0; i < 10; i++) {
      	try {
      		System.out.println("===========" + new HystrixCircuitBreaker(String.valueOf(i)).execute());
//      		try {
//          		TimeUnit.MILLISECONDS.sleep(1000);
//          	}catch(Exception e) {}
//      		Future<String> future = new HystrixCommand4CircuitBreakerTest("Hlx"+i).queue();
//      		System.out.println("===========" + future);
      	} catch(Exception e) {
      		System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
      	}
  	}

//  	System.out.println("------开始打印现有线程---------");
//  	Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
//  	for (Thread thread : map.keySet()) {
//			System.out.println(thread.getName());
//		}
//  	System.out.println("thread num: " + map.size());
  	
//  	System.in.read();
  }

}
