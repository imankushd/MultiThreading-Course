package com.ankush.udemy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable<Person>
{
	private int age;
	private String name;
	
	Person(int age,String name)
	{
		this.age=age;
		this.name=name;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name+" - "+age;
	}
}

class PWorker implements Runnable
{

	BlockingQueue<Person> queue;
	
	PWorker(BlockingQueue<Person> queue)
	{
		this.queue=queue;
	}
	
	@Override
	public void run() {
		try {
			queue.put(new Person(25,"Ankush"));
			queue.put(new Person(24,"Ankur"));
			queue.put(new Person(21,"Anshul"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class CWorker implements Runnable
{

	BlockingQueue<Person> queue;
	
	CWorker(BlockingQueue<Person> queue)
	{
		this.queue=queue;
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class PriorityQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Person> queue = new PriorityBlockingQueue<>(3);
		
		new Thread(new PWorker(queue)).start();
		new Thread(new CWorker(queue)).start();
		
	}

}
