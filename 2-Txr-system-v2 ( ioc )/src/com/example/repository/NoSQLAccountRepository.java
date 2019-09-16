package com.example.repository;


import com.example.model.Account;

public class NoSQLAccountRepository implements AccountRepository{


	public NoSQLAccountRepository() {
		System.out.println("NoSQLAccountRepository instance created..");
	}

	public Account loadAccount(String number) {
		// ... odm
		System.out.println("loading account " + number);
		return new Account(number, 1000.00);
	}

	public Account updateAccount(Account account) {
		// ...
		System.out.println("updating account " + account.getNumber());
		return account;
	}

}
