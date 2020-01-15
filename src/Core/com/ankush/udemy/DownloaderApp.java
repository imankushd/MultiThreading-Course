package com.ankush.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(4, true);
	
	void downloadData()
	{
		try {
			semaphore.acquire();
			download();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			semaphore.release();
		}
	}

	private void download() {
		// TODO Auto-generated method stub
		System.out.println("Downloading data from the web ...");
	}
	
}



public class DownloaderApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i=0 ; i<22 ;i++)
		{
			executor.submit(new Runnable() {
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
		
		
	}

}
