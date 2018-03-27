package com.tiancheng.springboot.chaptor03.dao;

import java.util.List;

import com.tiancheng.springboot.chaptor03.entity.Account;

public interface IAccountDAO {
	int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
