import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Account
{
	private int id;
	private String name;
	private String address;
	private double amount;
	
	public Account()
	{
		
	}
	public Account(int id, String name, String address, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", address=" + address + ", amount=" + amount + "]";
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public double getAmount() {
		return amount;
	}
	
}

class Producer implements Runnable
{
	Scanner sc = new Scanner(System.in);
	
	Account acc;
	
	private boolean exit=false;
	int j=1;
	Queue<Account> queue;
	int capacity;
	
	Producer(Queue<Account> queue,int capacity)
	{
		this.queue = queue;
		this.capacity = capacity;
	}

	@Override
	public void run() {
		while(!exit)
		{
			synchronized(queue)
			{
				System.out.println(Thread.currentThread().getName()+" : called....");
				while(!(queue.size()!=capacity))
				{
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
				acc = new Account(j, "Ankush:"+j, "Delhi:"+j, j*101.0);
				
				if(acc.getAmount()>0)
				{
				queue.add(acc);
				j++;
				queue.notify();
				}
			}
		}
		
	}
	
	public void stopProducer()
	{
		exit=true;
	}
	
}

class Consumer implements Runnable
{
	private boolean exit=false;

	Queue<Account> queue;
	
	FileWriter csvWriter;
	
	Consumer(Queue<Account> queue,FileWriter csvWriter)
	{
		this.queue = queue;
		this.csvWriter = csvWriter;
	}

	@Override
	public void run() {
		while(!exit)
		{
			synchronized(queue)
			{
				System.out.println(Thread.currentThread().getName()+" : called....");
				while(queue.isEmpty())
				{
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				Account acc = queue.remove();
				
				try {
					csvWriter.append(Integer.toString(acc.getId()));
					csvWriter.append(",");
					csvWriter.append(acc.getName());
					csvWriter.append(",");
					csvWriter.append(acc.getAddress());
					csvWriter.append(",");
					csvWriter.append(Double.toString(acc.getAmount()));
					csvWriter.append("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
				queue.notify();
			}
		}
	}
	public void stopConsumer()
	{
		exit=true;
	}

	
}


public class ProducerConsumerAssignment {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		Queue<Account> queue = new LinkedList<>();
		FileWriter csvWriter = new FileWriter("C:\\Users\\ANKUSH\\Desktop\\ankush programs\\prodcons.csv");
		
		csvWriter.append("Account ID");
		csvWriter.append(",");
		csvWriter.append("Account Holder's Name");
		csvWriter.append(",");
		csvWriter.append("Account Holder's Address");
		csvWriter.append(",");
		csvWriter.append("Total Amount");
		csvWriter.append("\n");
		
		int CAPACITY=0;
		int countAttempts=0;
		do {
			if(countAttempts>0)
			{
				System.out.println("Capacity should be grater than 1.");
				System.out.println("Please enter the capacity again.");
				CAPACITY=sc.nextInt();
			}
			else
			{
				System.out.println("Enter capacity of the queue: ");
				CAPACITY=sc.nextInt();
				countAttempts++;
			}
		}while(CAPACITY <= 0);
		
		Producer p = new Producer(queue,CAPACITY);
		Consumer c = new Consumer(queue,csvWriter);
		
		Thread producer = new Thread(p,"Producer");
		Thread consumer = new Thread(c,"Consumer");

		producer.start();
		consumer.start();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		p.stopProducer();
		c.stopConsumer();
		
		sc.close();
	}

}
