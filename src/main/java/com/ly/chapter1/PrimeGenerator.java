package com.ly.chapter1;

public class PrimeGenerator extends Thread {

	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.println("Number "+number+" is Prime");
			}
			
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted");
				return;
			}
			number++;
		}
	}

	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task.interrupt();
	}

	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}

		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}
}
