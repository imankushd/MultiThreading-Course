package com.ankush.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Wrker implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++)
		{
			System.out.println("Hey Ankush: " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class ExecutorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newCachedThreadPool();

		for(int i=0;i<3;i++)
		executor.submit(new Wrker());
		
	}
}
