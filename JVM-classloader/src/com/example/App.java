package com.example;

import com.example.container.Container;

public class App {

	public static void main(String[] args) {

		Container container = new Container();
		container.processRequest("/login");

	}

}
