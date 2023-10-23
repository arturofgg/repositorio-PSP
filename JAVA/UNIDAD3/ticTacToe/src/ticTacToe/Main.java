package ticTacToe;

public class Main {

    public Main() {

    }

    public static void main(String[] args) {
        tablero tablero = new tablero();
        Jugador jugador1 = new Jugador("Michael Jackson", 'X', tablero);
        Jugador jugador2 = new Jugador("Alexia Ashford", 'O', tablero);
        
        jugador1.start();
        jugador2.start();
    }
}
