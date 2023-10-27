package Principal;

public class SalaDeEspera  extends Thread
{

	private int capacidad;
	private int enEspera;
	private boolean sillaBarberoOcupada;
	
	public SalaDeEspera(int capacidad)
	{
		this.capacidad = capacidad;
		this.enEspera = 0;
		this.sillaBarberoOcupada = false;
	}

	public synchronized boolean entrar()
	{
		boolean exito=false;
		
		if( enEspera < capacidad)
		{
			exito = true;
			enEspera++;
		}
		else
		{
			exito =false;
		}
		return exito;
	}
	
	public synchronized void sentarseEnSillaBarbero() throws InterruptedException 
	{		
		while(sillaBarberoOcupada == true)
		{
			wait();
			sillaBarberoOcupada = true;
			enEspera--;
		}
//		System.out.println("El cliente "+((Cliente)Thread.currentThread()).getIdentificador()+ " es atendido");
//		Thread.currentThread().sleep(5000);
//		System.out.println("El cliente "+id+ " termina de ser atendido");
	}
}
