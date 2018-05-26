package com.tiancheng.hystrix.demo03;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class ObservableHelloWorld extends HystrixObservableCommand<String>{
	private String name ;
	
	public ObservableHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("testCommandGroup"));
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		// TODO Auto-generated method stub
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        // 在真实世界，run() 方法可能会产生一些网络请求等
                        observer.onNext("Hello");
                        observer.onNext(name + "!");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
		});
	}
	
	public static void main(String[] args) {
		ObservableHelloWorld commandHelloWorld = new ObservableHelloWorld("aa");
		Observable<String> result = commandHelloWorld.observe();
		System.out.println(result);
	}

}
