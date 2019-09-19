package com.example;

import java.util.concurrent.TimeUnit;

public class Ex2 {

	private static Object key = new Object();

	private static String food = null;

	public static void main(String[] args) {

		Thread hotel = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (key) {
					System.out.println("preparing biryani...");
					try {
						TimeUnit.SECONDS.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					food = "biryani...";
					System.out.println("biryani ready...");
					key.notify();
				}
			}
		},"hotel");

		Thread customer = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("customer need biryani..");
				synchronized (key) {
					if (food == null) {
						try {
							System.out.println("customer waiting for biryani..");
							key.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("customer having " + food);
				}
			}
		},"customer");

		customer.start(); // new stack...
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hotel.start();

	}

}
