package Principal;

public class Consumidor extends Thread {
	
	private int numeroPiezas=1;
	private GestorPiezas gestorPiezas;
	
	public Consumidor(int numeroPiezas, GestorPiezas gestorPiezas) {
		this.numeroPiezas=numeroPiezas;
		this.gestorPiezas= gestorPiezas;
	}
	
	public void run()
	{
		gestorPiezas.solicitarPiezas(numeroPiezas);
	}
}
