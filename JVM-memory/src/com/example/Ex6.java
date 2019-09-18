package com.example;

import java.math.BigDecimal;

public class Ex6 {

	public static void main(String[] args) {

		BigDecimal r = calculate();
		System.out.println(r.toString());

	}

	private static BigDecimal calculate() {
		// ...
		BigDecimal result = new BigDecimal(0);
		for (int i = 0; i < 100000; i++) {
			BigDecimal d = result.add(result);
			result = result.add(d);
		}
		return result;
	}

}
