package com.ly.chapter7;

import java.util.concurrent.Semaphore;

public class PrintQueue {
	private final Semaphore semaphore;

	public PrintQueue() {
		semaphore = new Semaphore(1);
	}
	
	public void printJob(Object document) {
		try {
			semaphore.acquire();
			long duration = (long) (Math.random() * 10);
			System.out.printf(
					"%s: PrintQueue: Printing a Job during %d seconds\n",
					Thread.currentThread().getName(), duration);
			Thread.sleep(duration);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
	
	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread[] thread = new Thread[10];
		for(int i = 0;i<10;i++){
			thread[i] =  new Thread(new Job(printQueue),"Thread "+i);
		}
		for(int i = 0;i < 10 ;i++){
			thread[i].start();
		}
	}
}
class Job implements Runnable{
	private PrintQueue printQueue;
	
	public Job(PrintQueue printQueue){
		this.printQueue = printQueue;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n",Thread. currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
	}
}















