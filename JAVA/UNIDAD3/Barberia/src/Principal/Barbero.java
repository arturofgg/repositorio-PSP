package Principal;

public class Barbero extends Thread
{

	private Barberia barberia;
	private boolean fin;
	
	public Barbero(Barberia barberia) {
		this.barberia = barberia;
		this.fin = false;
	}
	
	public void run()
	{	
		while(!fin) 
		{
			try {
				barberia.atenderCliente();
				sleep(5000); //Simula el tiempo que tarda en atender al cliente
				barberia.finAtenderCliente();
			} catch (InterruptedException e) { e.printStackTrace();}
		}
	}
}
