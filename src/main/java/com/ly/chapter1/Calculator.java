package com.ly.chapter1;
import java.io.File;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Calculator implements Runnable {
	private int number;
	public Calculator(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread()
					.getName(), number, i, i * number);
		}
	}

	public static void main(String[] args) {
		
		Thread[] threads = new Thread[10];
		
		Thread.State[] status = new Thread.State[10];
		
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("D:/log.txt"));
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : "
						+ threads[i].getState());
				status[i] = threads[i].getState();
			}
			
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish
							&& (threads[i].getState() == State.TERMINATED);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread,
			State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}
}
