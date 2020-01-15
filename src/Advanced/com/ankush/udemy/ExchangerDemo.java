package com.ankush.udemy;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable
{

	private int counter;
	private Exchanger<Integer> exchanger;
	
	FirstThread(Exchanger<Integer> exchanger)
	{
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			counter=counter+1;
			try {
				counter = exchanger.exchange(counter);
				System.out.println("FirstThread : "+counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class SecondThread implements Runnable
{

	private int counter;
	private Exchanger<Integer> exchanger;
	
	SecondThread(Exchanger<Integer> exchanger)
	{
		this.exchanger = exchanger;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			counter=counter-1;
			try {
				counter = exchanger.exchange(counter);
				System.out.println("SecondThread : "+counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}

public class ExchangerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Exchanger<Integer> exchanger = new Exchanger<Integer>();
		
		new Thread(new FirstThread(exchanger)).start();
		new Thread(new SecondThread(exchanger)).start();
		
	}

}
