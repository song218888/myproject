package com.tiancheng.springboot.chaptor03.service;

import java.util.List;

import com.tiancheng.springboot.chaptor03.entity.Account;

public interface IAccountService {


    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();

}
