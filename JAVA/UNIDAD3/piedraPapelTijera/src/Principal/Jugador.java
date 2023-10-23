package Principal;

public class Jugador {

    private String nombre;
    private String jugada;
    private int puntos;
    private boolean fin;
    private Banca banca;

    public Jugador(String nombre, String jugada, Banca banca) {
        this.nombre = nombre;
        this.jugada = jugada;
        this.puntos = 0;
        this.fin = false;
        this.banca =  banca;
    }

    public void start() {
        while(!fin) {
            if (banca.jugar(jugada)) {
                puntos++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else
                fin = true;
        }
        System.out.println(nombre + " termina con " + puntos + " puntos");
    }
}