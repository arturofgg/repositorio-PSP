package Principal;

public class Main 
{
	public static void main(String[] args) 
	{
		SalaDeEspera SalaDeEspera = new SalaDeEspera(4);
		
		Cliente cliente = new Cliente(1,SalaDeEspera);
	}
}
