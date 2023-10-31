package Principal;

public class Cliente extends Thread
{
	private int id;
	private Barberia Barberia;

	public Cliente(int id, Barberia Barberia) 
	{
		this.id=id;
		this.Barberia = Barberia;
	}
	
	public int getIdentificador() {
		return this.id;
	}

	public void run() 
	{
		boolean exito;
		exito = Barberia.entrar();
		if(exito==true)
		{
			try {
				System.out.println("El cliente "+id+" se sienta a esperar");
				Barberia.sentarseEnSillaBarbero();
				System.out.println("El cliente "+id+ " termina de ser atendido");
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		else
		{
			System.out.println("El cliente "+id+" se marcha porque no hay sillas libres");
 			
		}
	}
}
