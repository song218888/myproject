package com.tianchen.concurrency.disruptor.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorDemo01 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		

		// 线程池
		ExecutorService executor = Executors.newFixedThreadPool(1);

		// 事件策略
		WaitStrategy blockingWaitStrategy = new BlockingWaitStrategy();
//		WaitStrategy sleepingWaitStrategy = new SleepingWaitStrategy();
//		WaitStrategy yieldingWaitStrategy = new YieldingWaitStrategy();

		// 事件工厂
		EventFactory<HelloEvent> eventFactory = new HelloEventFactory();

		// RingBuffer大小，必须是2的N次方
		int ringBufferSize = 1024 * 1024;

		// 创建Disruptor
		Disruptor<HelloEvent> disruptor = new Disruptor<HelloEvent>(eventFactory, ringBufferSize, executor,
				ProducerType.SINGLE, blockingWaitStrategy);

	
		//事件处理器
		EventHandler<HelloEvent> eventHandler = new HelloEventHandler();
		disruptor.handleEventsWith(eventHandler);
		disruptor.start();
	
		//获取RingBuffer
		RingBuffer<HelloEvent> ringBuffer = disruptor.getRingBuffer();
		
		long sequence = ringBuffer.next();
		try{
			HelloEvent event = ringBuffer.get(sequence);
			event.setValue("hello world");
		}finally {
			ringBuffer.publish(sequence);;
		}
	}
}
