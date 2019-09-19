package com.example.service;


import com.example.model.Account;
import com.example.repository.SQLAccountRepository;

public class TxrServiceImpl {


	public TxrServiceImpl() {
		System.out.println("TxrServiceImpl instance created...");
	}

	public boolean transfer(double amount, String fromAccNumber, String toAccNumber) {

		System.out.println("txr initiated...");

		SQLAccountRepository accountRepository = new SQLAccountRepository();
		Account fromAccount = accountRepository.loadAccount(fromAccNumber);
		Account toAccount = accountRepository.loadAccount(toAccNumber);

		if (fromAccount.getBalance() < amount)
			throw new AccountBalanceException("No enough funds");

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.updateAccount(fromAccount);
		accountRepository.updateAccount(toAccount);

		System.out.println("txr completed...");

		return true;

	}

}
