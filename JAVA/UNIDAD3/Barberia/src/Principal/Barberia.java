package Principal;

public class Barberia
{
	private int capacidad=10;
	private int enEspera = 0;
	private boolean sillaBarberoOcupada = false;
	private boolean finServicio;
	
	public Barberia(int capacidad)
	{
		this.capacidad = capacidad;
		this.enEspera = 0;
		this.sillaBarberoOcupada = false;
		this.finServicio= false;
	}
	
	public synchronized void atenderCliente() throws InterruptedException
	{
		while(sillaBarberoOcupada==false)
		{
			wait();
		}
		finServicio = false;
	}
	
	public synchronized void finAtenderCliente()
	{
		sillaBarberoOcupada = false;
		finServicio = true;
		notifyAll();
	}
	
	public synchronized void sentarseEnSillaBarbero() throws InterruptedException 
	{		
		while(sillaBarberoOcupada == true)
		{
			wait();
		}
		sillaBarberoOcupada = true; // habra que despertar al barbero
		enEspera--; 
		notifyAll(); //Despertamos a todos para que se despierte el barbero
		while(finServicio == false)
		{
			wait();
		}
		sillaBarberoOcupada = false; //El cliente se levanta y se marcha
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
}
