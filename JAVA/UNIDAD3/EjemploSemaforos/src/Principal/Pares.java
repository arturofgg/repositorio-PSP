package Principal;

import java.util.concurrent.Semaphore;

public class Pares extends Thread {

	private int contador;
	private Semaphore semaforoP;
	private Semaphore semaforoI;
	
	public Pares(Semaphore semaforoP, Semaphore semaforoI) 
	{
		this.contador = 2;
		this.semaforoP = semaforoP;
		this.semaforoI = semaforoI;
	}


	public void run()
	{
		int i;
		for(i=0;i<10;i++)
		{
			try {
				semaforoP.acquire();
				
					System.out.println(contador);
					contador+=2;
					try {
						sleep (500);
					} catch (InterruptedException e) {e.printStackTrace();}
			}catch (InterruptedException e) {e.printStackTrace();}
			finally {semaforoI.release();}
		}
	}
}
