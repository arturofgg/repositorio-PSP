package Principal;

public class Main extends Thread{

	public static void main(String[] args) throws InterruptedException 
	{
		Concurrencia1 C[] = new Concurrencia1[6];
		
		for (int i=0;i<6;i++)
		{
			sleep(i*1000);
			C[i] = new Concurrencia1("Soy el hilo " +i);
			C[i].start();
		}
	}
}
