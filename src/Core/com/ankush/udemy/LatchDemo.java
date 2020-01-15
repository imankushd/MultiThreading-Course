package com.ankush.udemy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerLatch implements Runnable
{
	private int id;
	private CountDownLatch latch;
	
	WorkerLatch(int id , CountDownLatch latch)
	{
		this.id=id;
		this.latch=latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		doWork();
		latch.countDown();
	}

	private void doWork() {
		// TODO Auto-generated method stub
		System.out.println("thread with id "+this.id+" starts working...");
	}
	
}

public class LatchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch latch = new CountDownLatch(5);
		
		for(int i=0;i<5;i++)
		{
			executorService.execute(new WorkerLatch(i+1, latch));
			//System.out.println(latch.getCount());
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All prerequisties done...");
	}

}
