package com.example;

import org.springframework.boot.SpringApplication;

import com.example.config.TxrSystemConfiguration;

public class App {

	public static void main(String[] args) {
		
		// ------------------------------------------
		// Init/boot
		// ------------------------------------------
		SpringApplication.run(TxrSystemConfiguration.class, args);
		
		
	}

}
