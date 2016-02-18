package com.ly.chapter1;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable{
	private String initPath;
	
	private String fileName;
	
	public FileSearch(String initPath,String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		File file = new File(initPath);
		if(file.isDirectory()){
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted", Thread
						.currentThread().getName());
			}
		}
	}
	
	private void directoryProcess(File file) throws InterruptedException {
		File[] list = file.listFiles();
		if(list!=null){
			for(int i =0;i<list.length;i++){
				if(list[i].isDirectory()){
					directoryProcess(list[i]);
				}else{
					if(fileProcess(list[i])){
						return;
					}
				}
			}
		}
	}
	
	private boolean fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(),
					file.getAbsolutePath());
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		FileSearch search = new FileSearch("E:\\", "SJ_Test.java");
		Thread thread = new Thread(search);
		thread.start();
//		try {
			System.out.println("thread state ---> "+thread.getState());
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		thread.interrupt();
	}
}
