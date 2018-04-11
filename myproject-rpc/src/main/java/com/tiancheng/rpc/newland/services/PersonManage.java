package com.tiancheng.rpc.newland.services;

import com.tiancheng.rpc.newland.services.pojo.Person;

public interface PersonManage {
	
	int save(Person p);
	
	void query(Person p);
	
	void check();
	
	boolean checkAge(Person p);
}
