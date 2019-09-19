package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.web.TxrRequest;

@Service("txrService")
public class TxrServiceImpl implements TxrService {

	private AccountRepository accountRepository;
	private TxrRequestQueue txrRequestQueue;

	public TxrServiceImpl(@Qualifier("jpa") AccountRepository accountRepository, TxrRequestQueue txrRequestQueue) {
		this.accountRepository = accountRepository;
		this.txrRequestQueue = txrRequestQueue;
	}

	@PostConstruct
	public void init() {
		class ProcessTxrRequestTask implements Runnable {
			@Override
			public void run() {
				while (true) {
					TxrRequest request = txrRequestQueue.getNextTxrRequest();
					if (request == null) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						txr(request.getAmount(), request.getFromAccNum(), request.getToAccNum());
					}
				}

			}

		}
		Thread thread = new Thread(new ProcessTxrRequestTask(), "ProcessTxrRequest");
		thread.start();
	}

	@Transactional
	public boolean txr(double amount, String fromAccNum, String toAccNum) {

		System.out.println("Txr initiated...");

		// load 'from' & 'to' accounts
		Account fromAccount = accountRepository.findById(fromAccNum).get();
		Account toAccount = accountRepository.findById(toAccNum).get();

//		if(fromAccount.getBalance()<amount)
//			throw new AccountBalanceException("No enough funds");

		// debit & credit
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		// update accounts
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);

		System.out.println("Txr completed...");

		return true;
	}

}
