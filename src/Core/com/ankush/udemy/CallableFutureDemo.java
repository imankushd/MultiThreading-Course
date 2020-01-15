package com.ankush.udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Worker1 implements Callable<String>
{

	private int id;
	
	Worker1(int id)
	{
		this.id=id;
	}
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		    Thread.sleep(1000);
			return "Ankush: "+id;
	}
	
}

public class CallableFutureDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		List<Future<String>> al =new ArrayList<Future<String>>();
		
		for(int i=0;i<7;i++)
		{
			Future<String> future = executor.submit(new Worker1(i+1));
			al.add(future);
		}
		
		for(int i=0;i<al.size();i++)
		{
			try {
				System.out.println(al.get(i).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

}
