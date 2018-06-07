package com.tianchen.concurrency.disruptor.demo01;

import com.lmax.disruptor.EventFactory;

public class HelloEventFactory implements EventFactory<HelloEvent>{

	@Override
	public HelloEvent newInstance() {
		return new HelloEvent();
	}

}
