package com.ankush.udemy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayWorker implements Delayed
{

	private long duration;
	private String message;
	
	public DelayWorker(long duration,String message) {
		this.duration = duration+System.currentTimeMillis();
		this.message = message;
	}
	
	
	@Override
	public int compareTo(Delayed otherDelayed) {
		
		if(this.duration < ((DelayWorker) otherDelayed).getDuration())
			return -1;
		
		if(this.duration > ((DelayWorker) otherDelayed).getDuration())
			return +1;
		
		return 0;
	}

	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
	}


	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.message;
	}
}

public class DelayQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<DelayWorker> queue = new DelayQueue<>();
		
		try {
			queue.put(new DelayWorker(1000, "This is my first message"));
			queue.put(new DelayWorker(7000, "This is my second message"));
			queue.put(new DelayWorker(4000, "This is my third message"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Consuming elements in order...");
		
		while(!queue.isEmpty())
		{
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
