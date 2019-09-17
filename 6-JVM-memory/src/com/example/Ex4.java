package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class TxrRequest {

	private int id;
	private String fromAccNumber;
	private String toAccNumber;
	private double balance;

	public TxrRequest(String fromAccNumber, String toAccNumber, double balance) {
		super();
		this.fromAccNumber = fromAccNumber;
		this.toAccNumber = toAccNumber;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public String getFromAccNumber() {
		return fromAccNumber;
	}

	public String getToAccNumber() {
		return toAccNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFromAccNumber(String fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}

	public void setToAccNumber(String toAccNumber) {
		this.toAccNumber = toAccNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}

class TxrRequestQueue {

	private List<TxrRequest> requests = new ArrayList<TxrRequest>();
	private int nextAvaialbleId = 0;
	private int lastProcessedId = -1;

	public void addTxrRequest(TxrRequest request) {
		synchronized (this) {
			request.setId(nextAvaialbleId);
			synchronized (requests) {
				requests.add(request);
			}
			nextAvaialbleId++;
		}
	}

	public Optional<TxrRequest> getNextTxrRequest() {

		if (lastProcessedId + 1 > nextAvaialbleId) {
			lastProcessedId++;
			return Optional.of(requests.remove(lastProcessedId));
		} else
			return Optional.empty();

//		if (requests.size() > 0) {
//			synchronized (requests) {
//				return Optional.of(requests.remove(0));
//			}
//		} else
//			return Optional.empty();

	}

	public void printSummary() {
		int size = requests.size();
		System.out.println("" + new Date() + " requests in queue : " + size + " of " + nextAvaialbleId);
	}

}

class GenerateTxrRequest implements Runnable {

	private TxrRequestQueue txrRequestQueue;

	public GenerateTxrRequest(TxrRequestQueue txrRequestQueue) {
		super();
		this.txrRequestQueue = txrRequestQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// This is just to slow things down so we can see what's going on!
				Thread.sleep(2);
			} catch (InterruptedException e) {
			}
			String fromAccNumber = UUID.randomUUID().toString();
			String toAccNumber = UUID.randomUUID().toString();

			TxrRequest request = new TxrRequest(fromAccNumber, toAccNumber, 100.00);
			txrRequestQueue.addTxrRequest(request);
		}
	}

}

class ProcessTxrRequest implements Runnable {

	private TxrRequestQueue txrRequestQueue;

	public ProcessTxrRequest(TxrRequestQueue txrRequestQueue) {
		super();
		this.txrRequestQueue = txrRequestQueue;
	}

	@Override
	public void run() {
		while (true) {

			Optional<TxrRequest> request = txrRequestQueue.getNextTxrRequest();
			if (!request.isPresent()) {
				// no customers in queue so pause for half a second
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// Processing takes place here
			}
		}

	}

}

public class Ex4 {

	public static void main(String[] args) {

		TxrRequestQueue txrRequestQueue = new TxrRequestQueue();
		GenerateTxrRequest generateTxrRequest = new GenerateTxrRequest(txrRequestQueue);
		ProcessTxrRequest processTxrRequest = new ProcessTxrRequest(txrRequestQueue);

		for (int user = 0; user < 10; user++) {
			Thread t = new Thread(generateTxrRequest);
			t.start();
		}
		Thread t = new Thread(processTxrRequest);
		t.start();

		// main thread is now acting as the monitoring thread
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txrRequestQueue.printSummary();
			System.out.println("Available memory: " + Runtime.getRuntime().freeMemory() / 1024 + "k");

		}

	}

}
