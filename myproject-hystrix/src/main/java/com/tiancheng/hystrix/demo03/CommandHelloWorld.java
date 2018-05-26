package com.tiancheng.hystrix.demo03;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String>{

	private String name ;
	
	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("testCommandGroup"));
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		// TODO Auto-generated method stub
		return " hello : " + name;
	}
	
	public static void main(String[] args) {
		CommandHelloWorld commandHelloWorld = new CommandHelloWorld("aa");
		String result = commandHelloWorld.execute();
		System.out.println(result);
		
	}

}
