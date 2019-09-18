package com.example.repository;

import com.example.model.Account;

public class SQLAccountRepository {

	public SQLAccountRepository() {
		System.out.println("SQLAccountRepository instance created..");
	}

	public Account loadAccount(String number) {
		// ... jdbc / jpa
		System.out.println("loading account " + number);
		return new Account(number, 1000.00);
	}

	public Account updateAccount(Account account) {
		// ...
		System.out.println("updating account " + account.getNumber());
		return account;
	}

}
