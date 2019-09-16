package com.example.repository;

import org.apache.log4j.Logger;

import com.example.model.Account;

public class NoSQLAccountRepository implements AccountRepository{

	private static final Logger logger = Logger.getLogger("txr-system");

	public NoSQLAccountRepository() {
		logger.info("NoSQLAccountRepository instance created..");
	}

	public Account loadAccount(String number) {
		// ... odm
		logger.info("loading account " + number);
		return new Account(number, 1000.00);
	}

	public Account updateAccount(Account account) {
		// ...
		logger.info("updating account " + account.getNumber());
		return account;
	}

}
