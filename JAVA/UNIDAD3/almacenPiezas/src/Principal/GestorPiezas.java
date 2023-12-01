package Principal;

public class GestorPiezas {

	private int CantPiezas=0;		
	
	public GestorPiezas() {
		
	}
	
		public synchronized void solicitarPiezas(int numero)
		{
			while (numero>CantPiezas) {
				try {
					wait();
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			CantPiezas=CantPiezas-numero;
			System.out.println("El numero actual de piezas es "+CantPiezas+" despues de solicitarlas");
		}
		
		public synchronized void agregarPiezas(int numero) 
		{
			CantPiezas=CantPiezas+numero;
			System.out.println("El numero actual de piezas es "+CantPiezas+" despues de agregarlas");
			notifyAll();
		}
}
