package com.tiancheng.hystrix.demo01;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HystrixCommand4Semaphore extends HystrixCommand<String>{

	private final String name;
	
	public HystrixCommand4Semaphore(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreTestGroup"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("SemaphoreTestKey"))
				.andCommandPropertiesDefaults(// 配置信号量隔离
                		HystrixCommandProperties.Setter()
                		.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)	// 信号量隔离
                		.withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                		.withFallbackIsolationSemaphoreMaxConcurrentRequests(1))
				// 设置了信号量隔离后，线程池配置将变无效
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SemaphoreThreadPoolKey"))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(13)	// 配置线程池里的线程数
				)
			);
		this.name = name;
	}



	@Override
	protected String run() throws Exception {
		return "run(): name="+name+"，线程名是" + Thread.currentThread().getName();
	}
	
	@Override
    protected String getFallback() {
    	return "getFallback(): name="+name+"，线程名是" + Thread.currentThread().getName();
}
}
