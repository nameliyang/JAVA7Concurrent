package com.ly.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
	 
	@Override
	public void run() {
		System.out.println("233243");
		
		
		for (int i = 0; i < 10; i++) {
				 
			System.out.printf("%s\n", new Date());
			try { 
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.printf("The FileClock has been interrupted");
			}
		}  
 
	}
	public static void main(String[] args){
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
	}

}
