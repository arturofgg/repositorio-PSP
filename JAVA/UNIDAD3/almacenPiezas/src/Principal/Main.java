package Principal;

public class Main {

	public static void main(String[] args) {
		
		GestorPiezas gestorPiezas = new GestorPiezas();
		Consumidor C[] = new Consumidor[5];
		Productor P[] = new Productor[5];
		for (int i=0;i<5;i++)
		{
			C[i] = new Consumidor(i, gestorPiezas);
			P[i] = new Productor(i, gestorPiezas);
		}
		
		for (int i=0;i<5;i++)
		{
			C[i].start();
		}
		
		for (int i=0;i<5;i++)
		{
			P[i].start();
		}
	}
}
