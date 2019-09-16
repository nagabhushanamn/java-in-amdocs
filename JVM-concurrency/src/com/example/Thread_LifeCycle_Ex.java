package com.example;

class LoopTask implements Runnable {
	
	// READY_TO_RUN --> RUNNING 
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 1000; i++) {
			System.out.println(name + "->" + i);
			
			// --> READY_TO_RUN
			// or
			// --> NOT_READY_TO_RUN  ( sleep , blocked , wait )
			
		}
	}
	// --> DEAD
}

public class Thread_LifeCycle_Ex {

	public static void main(String[] args) {

		LoopTask loopTask = new LoopTask();

		Thread thread1 = new Thread(loopTask, "boy");  // New
		Thread thread2 = new Thread(loopTask, "girl");

		thread1.start();  // allocate new stack-memory  , READY_TO_RUN
		thread2.start();

	}

}
