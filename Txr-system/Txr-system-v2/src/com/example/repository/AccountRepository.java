package com.example.repository;

import com.example.model.Account;

public interface AccountRepository {
	public Account loadAccount(String number);

	public Account updateAccount(Account account);
}
