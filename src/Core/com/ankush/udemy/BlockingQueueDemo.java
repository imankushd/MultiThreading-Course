package com.ankush.udemy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
	
	int counter=1;
	
	private BlockingQueue<Integer> queue;
	
	Producer(BlockingQueue<Integer> queue)
	{
		this.queue=queue;
	}
	

	@Override
	public void run() {
		while(true)
		{
			try {
				queue.put(counter);
				System.out.println("Produced : "+counter);
				counter++;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Consumer implements Runnable {
	
	int counter=1;
	
	private BlockingQueue<Integer> queue;
	
	Consumer(BlockingQueue<Integer> queue)
	{
		this.queue=queue;
	}
	

	@Override
	public void run() {
		while(true)
		{
			try {
				int num=queue.take();
				System.out.println("Consumed : "+num);
				counter++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class BlockingQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
		
	}

}


