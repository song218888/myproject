package com.tianchen.concurrency.disruptor.demo01;

import com.lmax.disruptor.EventHandler;


public class HelloEventHandler implements EventHandler<HelloEvent> {

	@Override
	public void onEvent(HelloEvent event, long sequence, boolean endOfBatch) throws Exception {

		System.out.println(event.getValue());
	}

}
