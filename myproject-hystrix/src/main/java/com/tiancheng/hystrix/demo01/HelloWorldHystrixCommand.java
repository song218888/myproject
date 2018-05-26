package com.tiancheng.hystrix.demo01;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {

	private String name;

	public HelloWorldHystrixCommand(String name) {
		// 最少配置:指定命令组名(CommandGroup)
		 super(HystrixCommandGroupKey.Factory.asKey("aa"));

//		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testCommandGroupKey"))
//				.andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
//				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("testThreadPool"))
//				.andCommandPropertiesDefaults(
//						HystrixCommandProperties.Setter()
//								.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
//								.withExecutionTimeoutInMilliseconds(5000)
//								.withCircuitBreakerEnabled(true)
//						)
//				);
		
//		HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true);
//		HystrixCommandProperties.Setter().withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE);
//		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000);
//		HystrixThreadPoolProperties.Setter().withCoreSize(1);
		
		this.name = name;

	}
	
//	@Override
//	protected String getFallback() {
//		System.out.println("触发了降级!");
//      return "exeucute fallback";
//	}

	@Override
	protected String run(){
		for (int i = 0; i < 10; i++) {
			System.out.println("runing HelloWorldHystrixCommand..." + i);
		}
		return "【Hello " + name + "! thread:" + Thread.currentThread().getName()+"】";
		
	}

}
