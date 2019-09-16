package com.example;

import com.example.repository.AccountRepository;
import com.example.repository.NoSQLAccountRepository;
import com.example.repository.SQLAccountRepository;
import com.example.service.TxrService;
import com.example.service.TxrServiceImpl;

public class App {

	public static void main(String[] args) {
		
		// container ==> web-container , EJB-container or spring f.w container

		// 1. init / boot
		AccountRepository sqlAccountRepository = new SQLAccountRepository();
		AccountRepository noSqlAccountRepository = new NoSQLAccountRepository();
		TxrService txrService = new TxrServiceImpl(noSqlAccountRepository);
		System.out.println();

		// 2. use
		txrService.transfer(100.00, "1", "2");
		System.out.println();
		txrService.transfer(100.00, "2", "1");
		System.out.println();

		// 3. destroy
		txrService = null;

	}

}
