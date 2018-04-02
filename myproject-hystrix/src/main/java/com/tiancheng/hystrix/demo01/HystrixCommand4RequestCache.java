package com.tiancheng.hystrix.demo01;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/**
 * cache只有同在一个context中才生效
 * 通过HystrixRequestContext.initializeContext()初始化context，通过shutdown()关闭context
 */
public class HystrixCommand4RequestCache extends HystrixCommand<Boolean> {
	private final int value;
	private final String value1;

	public HystrixCommand4RequestCache(int value, String value1) {
		super(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommandGroup"));
		this.value = value;
		this.value1 = value1;
	}

	// 返回结果是cache的value
	@Override
	protected Boolean run() {
		return value == 0 || value % 2 == 0;
	}

	// 构建cache的key
	@Override
	protected String getCacheKey() {
		return String.valueOf(value) + value1;
	}
}
