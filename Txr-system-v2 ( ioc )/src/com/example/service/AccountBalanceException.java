package com.example.service;

@SuppressWarnings("serial")
public class AccountBalanceException extends RuntimeException {
	public AccountBalanceException(String message) {
		super(message);
	}
}
