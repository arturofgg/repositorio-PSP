package Principal;

public class Concurrencia1 extends Thread
{
	public Concurrencia1(String str) {
		super(str);
	}
	
	public void run()
	{
		System.out.println("Hola mundo "+getName());
	}
}