package Principal;

public class Cliente extends Thread
{
	private int id;
	private SalaDeEspera SalaDeEspera;

	public Cliente(int id, SalaDeEspera SalaDeEspera) 
	{
		this.id=id;
		this.SalaDeEspera = SalaDeEspera;
	}
	
	public int getIdentificador() {
		return this.id;
	}

	public void run() 
	{
		boolean exito;
		exito = SalaDeEspera.entrar();
		if(exito==true)
		{
			try {
				SalaDeEspera.sentarseEnSillaBarbero();
				//System.out.println("El cliente "+((Cliente)Thread.currentThread()).getIdentificador()+ " es atendido");
				System.out.println("El cliente "+id+ " comienza de ser atendido");
				sleep(5000);
				System.out.println("El cliente "+id+ " termina de ser atendido");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("El cliente "+id+" se marcha porque no hay sillas libres");
 			
		}
	}
}
