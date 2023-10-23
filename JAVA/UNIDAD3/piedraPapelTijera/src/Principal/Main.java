package Principal;

public class Main {

	public static void main(String[] args) {
		Banca miBanca = new Banca();
		Jugador[] jugadores = new Jugador[100];
		
		for(int i = 0; i<100 ;i++)
			jugadores[i] = new Jugador ("J"+i,apuesta(),miBanca);
		
		for(int i=0;i<100;i++)
			jugadores[i].start();
	}
	static String apuesta()
	{
		String resultado="";
		int numero = (int)(Math.random()*3+1);
		switch (numero) {
		case 1: resultado="Piedra"; break;
		case 2: resultado="Papel"; break;
		case 3: resultado="Tijera"; break;
		}
		return resultado;
	}
}