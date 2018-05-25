package com.tiancheng.hystrix.demo01;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class HystrixCommand4QueueTest {
	@Test
	public void testQueue() throws Exception {
		// queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
		// 一个对象只能queue()一次
		// queue()事实上是toObservable().toBlocking().toFuture()
		Future<String> future = new HelloWorldHystrixCommand("Hlx").queue();
		System.out.println("===========");
		
		// 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
		String queueResult = future.get(5000, TimeUnit.MILLISECONDS);
		// String queueResult = future.get();
		System.out.println("queue异步结果：" + queueResult);
		assertEquals("Hello", queueResult.substring(0, 5));
}
	
	
}
