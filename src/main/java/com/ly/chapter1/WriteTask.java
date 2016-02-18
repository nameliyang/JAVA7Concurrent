package com.ly.chapter1;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriteTask implements Runnable {
	
	private Deque<Event> deque;

	public WriteTask(Deque<Event> deque) {
		this.deque = deque;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			System.out.println(Thread.currentThread().getName()+" set event i = "+i +" size :"+deque.size() );
			event.setEvent(String.format(
					"The thread %s has generated an   event", Thread
							.currentThread().getId()));
			deque.addFirst(event);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Deque<Event> deque = new ArrayDeque<Event>();
		
		WriteTask writer = new WriteTask(deque);
		
		for(int i = 0;i<3;i++){
			Thread thread = new Thread(writer);
			thread.start();
		}
		
		CleanTask cleaner = new CleanTask(deque);
		cleaner.start();
		
	}
}

class CleanTask extends Thread{
	
	private Deque<Event> deque;
	
	public CleanTask(Deque<Event> deque){
		this.deque = deque;
		setDaemon(true);
	}
	
	@Override
	public void run() {
		System.out.println("------start clean data-----");
		while(true){
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;
//		System.out.println("size "+deque.size());
		if (deque.size() == 0) {
			return;
		}
		delete = false;
		
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
//			System.out.println(difference);
			if (difference > 10000) {
//				System.out.printf("Cleaner: %s\n", e.getEvent());
				System.out.println("Cleaner: "+e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
		}
	}
}

class Event {
	private String event;
	private Date date;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}