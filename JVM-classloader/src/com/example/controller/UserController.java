package com.example.controller;

import com.example.container.RequestMapping;
import com.example.container.RequestMethod;

public class UserController {
	

	@RequestMapping(url = "/login", method = RequestMethod.POST)
	public void auth() {
		System.out.println("login..");
	}

	@RequestMapping(url = "/register", method = RequestMethod.POST)
	public void doRegister() {
		System.out.println("register..");
	}

}
