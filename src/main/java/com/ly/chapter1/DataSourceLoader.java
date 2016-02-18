package com.ly.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

public class DataSourceLoader implements Runnable {

	@Override
	public void run() {
		System.out.printf("%s thread Beginning data sources loading: %s\n", Thread.currentThread(),new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s thread  Data sources loading has finished:%s\n",Thread.currentThread(),new Date());
	}
	public static void main(String[] args) {
		DataSourceLoader dsLoader= new DataSourceLoader();
		Thread thread1 = new Thread(dsLoader,"DataSourceThread");
		
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
	}
}

class NetworkConnectionsLoader  implements Runnable {

	@Override
	public void run() {
		System.out.printf("%s thread Beginning data sources loading: %s\n", Thread.currentThread(),new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s thread  Data sources loading has finished:%s\n",Thread.currentThread(), new Date());

	}
}
