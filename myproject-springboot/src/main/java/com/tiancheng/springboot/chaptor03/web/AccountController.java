package com.tiancheng.springboot.chaptor03.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiancheng.springboot.chaptor03.entity.Account;
import com.tiancheng.springboot.chaptor03.service.IAccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	IAccountService accountService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Account> getAccounts() {
		System.out.println("=============== getAccounts ====================");
		return accountService.findAccountList();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Account getAccountById(@PathVariable("id") int id) {
		System.out.println("=============== getAccountById ====================");
		return accountService.findAccountById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "money", required = true) double money) {
		System.out.println("=============== updateAccount ====================");
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		account.setId(id);
		int t = accountService.update(account);
		if (t == 1) {
			return account.toString();
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postAccount(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
		System.out.println("=============== postAccount ====================");
		Account account = new Account();
		account.setMoney(money);
		account.setName(name);
		int t = accountService.add(account);
		if (t == 1) {
			return account.toString();
		} else {
			return "fail";
		}

	}
}
