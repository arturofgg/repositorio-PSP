package Principal;

public class Productor extends Thread{

	private int numeroPiezas=1;
	private GestorPiezas gestorPiezas;
	
	public Productor(int numeroPiezas, GestorPiezas gestorPiezas) {
		this.numeroPiezas=numeroPiezas;
		this.gestorPiezas= gestorPiezas;
	}
	
	public void run()
	{
		try {
			System.out.println("Productor creando pieza");
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gestorPiezas.agregarPiezas(numeroPiezas);
	}

}
