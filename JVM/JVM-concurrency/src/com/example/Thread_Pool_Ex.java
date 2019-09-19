package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread_Pool_Ex {
	
	public static void main(String[] args) {
		
		// from JDK 1.5
		
		ExecutorService executorService=Executors.newSingleThreadExecutor();
		executorService=Executors.newFixedThreadPool(10);
		executorService=Executors.newCachedThreadPool();
		
	}

}
