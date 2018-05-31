package com.tiancheng.structurepattern.adapter;

interface JP110VInterface {
	public void connect();
}

class JP110VInterfaceImpl implements JP110VInterface {
	@Override
	public void connect() {
		System.out.println("接通110V电源，开始工作......");

	}
}

interface CN220VInterface {
	public void connect();
}

class CN220VInterfaceImpl implements CN220VInterface {
	@Override
	public void connect() {
		System.out.println("接通220V电源，开始工作......");

	}
}

class PowerAdaptor implements JP110VInterface {
	private CN220VInterface cn220VInterface;

	public PowerAdaptor(CN220VInterface cn220VInterface) {
		this.cn220VInterface = cn220VInterface;
	}

	@Override
	public void connect() {
		cn220VInterface.connect();
	}
}

class ElectricCooker {
	private JP110VInterface jp110VInterface;// 日本电饭煲，用的是日本110V标准电源接口

	ElectricCooker(JP110VInterface jp110VInterface) {
		this.jp110VInterface = jp110VInterface;
	}

	public void cook() {
		jp110VInterface.connect();// 接通电源
		System.out.println("开始做饭......");
	}
}

public class AdapterDemo01 {
	public static void main(String[] args) {
		CN220VInterface cn220VInterface = new CN220VInterfaceImpl();// 中国的220V电源
		PowerAdaptor adaptor = new PowerAdaptor(cn220VInterface);// 电源适配器
		ElectricCooker cooker = new ElectricCooker(adaptor);// 使用110V接口的电饭煲
		cooker.cook();// 使用了适配器，在220V的环境下可以工作啦
	}
}
