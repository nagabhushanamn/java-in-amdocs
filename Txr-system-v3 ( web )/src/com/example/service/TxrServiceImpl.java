package com.example.service;

import org.apache.log4j.Logger;

import com.example.model.Account;
import com.example.repository.AccountRepository;

public class TxrServiceImpl implements TxrService {

	private static final Logger logger = Logger.getLogger("txr-system");

	private AccountRepository accountRepository;

	public TxrServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		logger.info("TxrServiceImpl instance created...");
	}

	@Override
	public boolean transfer(double amount, String fromAccNumber, String toAccNumber) {

		logger.info("txr initiated...");

		Account fromAccount = accountRepository.loadAccount(fromAccNumber);
		Account toAccount = accountRepository.loadAccount(toAccNumber);

		if (fromAccount.getBalance() < amount)
			throw new AccountBalanceException("No enough funds");

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.updateAccount(fromAccount);
		accountRepository.updateAccount(toAccount);

		logger.info("txr completed...");

		return true;

	}

}
