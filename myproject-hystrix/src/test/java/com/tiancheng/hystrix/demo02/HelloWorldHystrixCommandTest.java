package com.tiancheng.hystrix.demo02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.tiancheng.hystrix.demo01.HelloWorldHystrixCommand;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class HelloWorldHystrixCommandTest {
	
	@Test
	public void testExecute(){
		
		//每个Command对象只能调用一次,不可以重复调用,  
        //重复调用对应异常信息:This instance can only be executed once. Please instantiate a new instance.  
        HelloWorldHystrixCommand helloWorldCommand = new HelloWorldHystrixCommand("Synchronous-hystrix");  
        //使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();   
        String result = helloWorldCommand.execute();  
        System.out.println("execute同步执行：" + result);
        
       
	}
	
	
	// queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
	// 一个对象只能queue()一次
	// queue()事实上是toObservable().toBlocking().toFuture()
	@Test
	public void testQueue(){
		HelloWorldHystrixCommand helloWorldCommand = new HelloWorldHystrixCommand("Asynchronous-hystrix");  
        //异步调用,可自由控制获取结果时机,  
        Future<String> future = helloWorldCommand.queue();
        String result = "";
        //get操作不能超过command定义的超时时间,默认:1秒  
        try {
			result = future.get(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("queue异步执行=" + result);  
        System.out.println("mainThread=" + Thread.currentThread().getName());  
	}
	
	
	
	@Test
	public void testObservable(){
		System.out.println("#####################");
		//注册观察者事件拦截
		Observable<String> fs = new HelloWorldHystrixCommand("World").observe();
		
		//注册结果回调事件
		 fs.subscribe(new Action1<String>() { 
		     @Override
		      public void call(String result) {
		           //执行结果处理,result 为HelloWorldCommand返回的结果
		          //用户对结果做二次处理.
		    	 System.out.println("=========");
		      }
		 });
		 
		 //注册完整执行生命周期事件
		 fs.subscribe(new Observer<String>() {
		             @Override
		             public void onCompleted() {
		                 // onNext/onError完成之后最后回调
		                 System.out.println("execute onCompleted");
		             }
		             @Override
		             public void onError(Throwable e) {
		                 // 当产生异常时回调
		                 System.out.println("onError " + e.getMessage());
		                 e.printStackTrace();
		             }
		             @Override
		             public void onNext(String v) {
		                 // 获取结果后回调
		                 System.out.println("onNext: " + v);
		             }
		         });
	}

}
