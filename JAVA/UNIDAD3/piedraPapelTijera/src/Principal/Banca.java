package Principal;

public class Banca {
	
	private String jugada;

	public Banca() {
		int numero = (int)(Math.random()*3+1);
		switch (numero) {
		case 1: jugada="Piedra"; break;
		case 2: jugada="Papel"; break;
		case 3: jugada="Tijera"; break;
		}
	}
	
	public  synchronized boolean jugar(String jugadaJugador){
		boolean resultado ;
		resultado = gana(jugadaJugador, jugada);
		if(resultado)
			jugada = jugadaJugador;
	return resultado;
	}
	
	public synchronized boolean gana(String a, String b){
		boolean ganar = false;
		if(a.compareTo("Tijera") == 0 && b.compareTo("Papel")==0)
			ganar=true;
		else 
			if (a.compareTo("Papel") == 0 && b.compareTo("Piedra")==0)
				ganar=true;
				else 
					 if (a.compareTo("Piedra") == 0 && b.compareTo("Tijera")==0)
						 ganar=true;
	return ganar;
	}
}