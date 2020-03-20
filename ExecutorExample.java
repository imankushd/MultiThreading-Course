package com.ankush;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable
{

	private int id;
	
	Processor(int id)
	{
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting Processor: "+id);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed Processor: "+id);
	}
	
}

public class ExecutorExample {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i=0;i<10;i++)
			executor.submit(new Processor(i));
		
		executor.shutdown();
		
		System.out.println("All tasks submitted");
		
		try {
			executor.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");
	}

}
