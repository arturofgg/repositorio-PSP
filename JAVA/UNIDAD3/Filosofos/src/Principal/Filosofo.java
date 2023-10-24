package Principal;

public class Filosofo extends Thread
{
	
	private int numero;
	private boolean fin;
	private Mesa mesa;
	
	public Filosofo(int numero, Mesa mesa) 
	{
		this.numero = numero;
		this.mesa = mesa;
	}

	public void run()
	{
		while(!fin) 
		{
			try {
				sleep ((long) (1000+Math.random()*5000));
			} catch (InterruptedException e) {e.printStackTrace(); }
			try {
				mesa.dameTenedores(numero);
			} catch (InterruptedException e) {e.printStackTrace(); }
			try
			{ 
				sleep ((long) (1000+Math.random()*3000));
			} catch (InterruptedException e) {e.printStackTrace(); }
			mesa.devolverTenedores(numero);
		}
	}
}
