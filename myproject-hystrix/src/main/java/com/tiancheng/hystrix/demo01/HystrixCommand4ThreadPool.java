package com.tiancheng.hystrix.demo01;

import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HystrixCommand4ThreadPool extends HystrixCommand<String> {

	private final String name;

	public HystrixCommand4ThreadPool(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolTest"))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3)));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		/*---------------会触发fallback的case-------------------*/
		// int j = 0;
		// while (true) {
		// j++;
		//// return "a";
		// }
		// 除零异常
		// int i = 1/0;

		// 主动抛出异常
		// throw new HystrixTimeoutException();
		// throw new RuntimeException("this command will trigger fallback");
		// throw new Exception("this command will trigger fallback");
		// throw new HystrixRuntimeException(FailureType.BAD_REQUEST_EXCEPTION,
		// commandClass, message, cause, fallbackException);

		/*---------------不会触发fallback的case-------------------*/
		// 被捕获的异常不会触发fallback
		// try {
		// throw new RuntimeException("this command never trigger fallback");
		// } catch(Exception e) {
		// e.printStackTrace();
		// }

		// HystrixBadRequestException异常由非法参数或非系统错误引起，不会触发fallback，也不会被计入熔断器
		// throw new HystrixBadRequestException("HystrixBadRequestException is
		// never trigger fallback");
		System.out.println("run : " + Thread.currentThread().getId());
		TimeUnit.MILLISECONDS.sleep(2000);
		return name;
	}
	
	
	@Override
    protected String getFallback() {
        return " fallback: " + name + " 【】" + Thread.currentThread().getId();
}

}
