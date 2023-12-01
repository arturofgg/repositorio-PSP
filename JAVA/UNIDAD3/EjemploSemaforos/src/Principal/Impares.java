package Principal;

import java.util.concurrent.Semaphore;

public class Impares extends Thread {

	private int contador;
	private Semaphore semaforoI;
	private Semaphore semaforoP;
	
	public Impares(Semaphore semaforoI, Semaphore semaforoP) 
	{
		this.contador = 1;
		this.semaforoI = semaforoI;
		this.semaforoP = semaforoP;
	}


	public void run()
	{
		int i;
		for(i=0;i<10;i++)
		{
			try {
				semaforoI.acquire();
				
					System.out.println(contador);
					contador+=2;
					try {
						sleep (500);
					} catch (InterruptedException e) {e.printStackTrace();}
			}catch (InterruptedException e) {e.printStackTrace();}
			finally {semaforoP.release();}
		}
	}
}
