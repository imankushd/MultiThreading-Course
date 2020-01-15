package com.ankush.udemy;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerBarrier implements Runnable{
	
	private int id;
	private CyclicBarrier barrier;
	private Random random;

	WorkerBarrier(int id, CyclicBarrier barrier)
	{
		this.id=id;
		this.barrier=barrier;
		this.random=new Random();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread with id "+id+" starts working...");
		
		try {
			Thread.sleep(random.nextInt(2000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			barrier.await();
			System.out.println("Thread with id "+id+" finished...");
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

public class CyclicBarrierDemo {

	public static void main(String... args)
	{
		CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
			public void run() {
				System.out.println("All tasks completed. Now we can go to next step...");
			}
		});
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		for(int i=0;i<5;i++)
			executorService.execute(new WorkerBarrier(i+1, barrier));
}
}
