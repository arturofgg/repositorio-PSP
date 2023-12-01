package Principal;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) 
	{
		Semaphore semaforoI = new Semaphore(1);
		Semaphore semaforoP = new Semaphore(0);
		Impares impares =  new Impares(semaforoI, semaforoP);
		Pares pares = new Pares(semaforoP, semaforoI);
		
		impares.start();
		pares.start();
	}

}
