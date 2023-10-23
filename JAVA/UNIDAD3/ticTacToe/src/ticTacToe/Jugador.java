package ticTacToe;

public class Jugador extends Thread {
    private String nombre;
    private char simbolo;
    private boolean fin;
    private tablero t;

    public Jugador(String nombre, char simbolo, tablero t) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.fin = false;
        this.t = t;
    }

    public void run() {
        int[] hueco;

        while (!fin) {
            hueco = t.getHueco();
            if (hueco[0] == 1) {
                t.ponerFicha(hueco[1], hueco[2], simbolo);
                try {
					if (t.comprobar(simbolo)) {
					    System.out.println("Ha ganado " + nombre);
					    fin = true;
					    if(simbolo=='X')
					    {
					    	t.imprimir();
					    }
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                System.out.println("Parece que hemos empatado");
                fin = true;
            }
        }
    }
}
