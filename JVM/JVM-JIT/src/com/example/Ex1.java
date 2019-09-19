package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class PrimeNumbers {

	private List<Integer> primes;

	private Boolean isPrime(Integer testNumber) {
		for (int i = 2; i < testNumber; i++) {
			if (testNumber % i == 0)
				return false;
		}
		return true;
	}

	private Integer getNextPrimeAbove(Integer previous) {
		Integer testNumber = previous + 1;
		while (!isPrime(testNumber)) {
			testNumber++;
		}
		return testNumber;
	}

	public void generateNumbers(Integer max) {
		primes = new ArrayList<Integer>();
		primes.add(2);

		Integer next = 2;
		while (primes.size() <= max) {
			next = getNextPrimeAbove(next);
			primes.add(next);
		}
//		 System.out.println(primes);
	}

}

public class Ex1 {

	public static void main(String[] args) throws InterruptedException {

//		Thread.sleep(20000);
		Date start=new Date();
		System.out.println("starting the work...");
		PrimeNumbers primeNumbers = new PrimeNumbers();
		Integer max = Integer.parseInt(args[0]);
		primeNumbers.generateNumbers(max);
		Date end=new Date();
		System.out.println("Time elapsed "+(end.getTime() - start.getTime())+" ms");
	
	
	}

}
