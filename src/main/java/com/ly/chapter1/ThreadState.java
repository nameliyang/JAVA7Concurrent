package com.ly.chapter1;

public class ThreadState {
	public static void main(String[] args) {
		final Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(thread.getState()!=Thread.State.TERMINATED){
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					System.out.println(thread.getState());
				}
			}
		}).start();
		thread.start();
//		System.out.println(thread.getState());
	}
}
