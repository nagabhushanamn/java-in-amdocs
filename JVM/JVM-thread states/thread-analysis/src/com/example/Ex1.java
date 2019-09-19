package com.example;

import java.util.concurrent.TimeUnit;

class Room implements Runnable {
	
	public synchronized void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + "-thread going to sleep");
		try {
			TimeUnit.MINUTES.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "-thread awake and unning");
	}
}

public class Ex1 {

	public static void main(String[] args) throws InterruptedException {

		Room room = new Room();

		Thread thread1 = new Thread(room, "boy");
		Thread thread2 = new Thread(room, "girl");

		thread1.start();
		thread2.start();
		
		

	}

}
