package Principal;

public class Main {

	public static void main(String[] args) {
		
		Jugador jugadores[] = new Jugador[10];
		for (int i=0;i<10;i++) {
			jugadores[i]=new Jugador(i,10);
			jugadores[i].start();
		}
	}
}