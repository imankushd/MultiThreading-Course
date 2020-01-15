package com.ankush.udemy;

class Worker implements Runnable {
	
	public boolean isTeminated=false;
	
	public boolean isTeminated() {
		return isTeminated;
	}

	public void setTeminated(boolean isTeminated) {
		this.isTeminated = isTeminated;
	}

	public void run() {
		
		while(!isTeminated)
		{
			System.out.println("Hello Ji");
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}


public class VolatileDemo {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Worker w = new Worker();
		Thread t = new Thread(w);
		t.start();
				
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		w.setTeminated(true);
		
		System.out.println("Finished processing");
		
		
	}

}
