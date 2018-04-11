package com.tiancheng.rpc.newland.services.impl;

import java.util.concurrent.TimeUnit;

import com.tiancheng.rpc.newland.services.PersonManage;
import com.tiancheng.rpc.newland.services.pojo.Person;

public class PersonManageImpl implements PersonManage {

	@Override
	public int save(Person p) {
		System.out.println("person data[" + p + "] has save!");
		return 0;
	}

	@Override
	public void query(Person p) {
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("person data[" + p + "] has query!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void check() {
		throw new RuntimeException("person check fail!");

	}

	@Override
	public boolean checkAge(Person p) {
		if (p.getAge() < 18) {
			throw new RuntimeException("person check age fail!");
		} else {
			System.out.println("person check age succ!");
			return true;
		}

	}

}
