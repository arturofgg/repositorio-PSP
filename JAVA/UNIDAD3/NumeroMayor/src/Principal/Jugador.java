package Principal;

public class Jugador extends Thread
{
	private Sala sala;
	private int identificador;
	private int numero;
	private boolean fin;
	
	public Jugador(int identificador, int numero, Sala sala) {
		this.identificador=identificador;
		this.numero=numero;
		this.fin=false;
		this.sala = sala;
	}
	
//	public int getNumero()
//	{
//		return numero;
//	}
	
//	public void setNumero(int numero) {
//		this.numero = numero;
//	}
	
	public void run()
	{
		while(!fin)
		{
			fin = sala.jugar(numero);
		}
	}

}
