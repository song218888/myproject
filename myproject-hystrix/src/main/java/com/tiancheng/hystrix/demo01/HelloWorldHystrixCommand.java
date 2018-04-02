package com.tiancheng.hystrix.demo01;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {

	private final String name;

	public HelloWorldHystrixCommand(String name) {
		// TODO Auto-generated constructor stub
		// super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCommandGroupKey"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("testCommandkey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("testThreadPool"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						// .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
						.withExecutionTimeoutInMilliseconds(5000)));

		// HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true);
		// HystrixCollapserProperties.Setter()
		// HystrixThreadPoolProperties.Setter().withCoreSize(1);
		this.name = name;
	}
	
//	@Override  
//  protected String getFallback() {
//		System.out.println("触发了降级!");
//      return "exeucute fallback";
//  }
	

	@Override
	protected String run() throws Exception {
		 for (int i = 0; i < 10; i++) {
		 System.out.println("runing HelloWorldHystrixCommand..." + i);
		 }
		
		 TimeUnit.MILLISECONDS.sleep(3000);
		return "Hello " + name + "! thread:" + Thread.currentThread().getName();
	}
}
