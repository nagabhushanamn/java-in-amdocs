package com.example;

import java.util.Scanner;

/*
 * 
 * 
 *  s/w appln
 *  -----------
 *  
 *  	- computation
 *      - io
 * 
 */

public class App {

	public static void computation() {
		System.out.println(Thread.currentThread().getName() + " => computation started");
		boolean b = true;
		while (b) {
//			System.out.print("..");
		}
		System.out.println(Thread.currentThread().getName() + "  => computation finished");
	}

	public static void io() {
		System.out.println(Thread.currentThread().getName() + " => io started");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter name!");
		String name = sc.nextLine();
		System.out.println("hello " + name);
		sc.close();
		System.out.println(Thread.currentThread().getName() + "  => io finished");
	}

	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName() + " start..");

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				io();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				computation();
			}
		});

		thread1.start();
		thread2.start();

	}

}
