package com.ankush;

class XYZ
{
	volatile int flag=1;
}

class Thread1 extends Thread
{
	
	XYZ lock;
	int n=1;
	int PRINT_UPTO = 100000;
	
	Thread1(XYZ lock)
	{
		this.lock = lock;
	}
	
	public void run()
	{
		synchronized (lock) {
			while(n < PRINT_UPTO)
			{
				while(lock.flag!=1)
				{
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(n);
				n++;
				if(n==100)
					n=1;
				lock.flag=2;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.notifyAll();
			}
		}
	}
}

class ThreadAA extends Thread
{
	
	XYZ lock;
	int n=1;
	
	ThreadAA(XYZ lock)
	{
		this.lock = lock;
	}
	
	public void run()
	{
		synchronized (lock) {
			while(n < 30)
			{
				while(lock.flag!=3)
				{
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				char ch = (char)(n+64);
				System.out.println(ch);
				n++;
				if(ch=='Z')
					n=1;
				lock.flag=1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.notifyAll();
			}
		}
	}
}

class Threada extends Thread
{
	
	XYZ lock;
	int n=1;
	
	Threada(XYZ lock)
	{
		this.lock = lock;
	}
	
	public void run()
	{
		synchronized (lock) {
			while(n < 30)
			{
				while(lock.flag!=2)
				{
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				char ch = (char)(n+96);
				System.out.println(ch);
				n++;
				if(ch=='z')
					n=1;
				lock.flag=3;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock.notifyAll();
			}
		}
	}
}

public class TenthQuestion
{
	
public static void main(String[] args)
{
	XYZ lock  = new XYZ();
	
	Thread1 t = new Thread1(lock);
	ThreadAA t1 = new ThreadAA(lock);
	Threada t2 = new Threada(lock);
	
	t.start();
	t1.start();
	t2.start();
	
}
}