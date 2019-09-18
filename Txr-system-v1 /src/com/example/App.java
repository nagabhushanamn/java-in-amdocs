package com.example;

import com.example.service.TxrServiceImpl;

public class App {

	public static void main(String[] args) {

		// 1. init / boot
		TxrServiceImpl txrService = new TxrServiceImpl();
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
