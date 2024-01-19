public class GestorSala {
    private static final int CAPACIDAD_REDUCIDA = 35;
    private static final int TEMPERATURA_UMBRAL = 30;

    private int personasEnSala = 0;
    private int temperaturaActual = 25; // Temperatura inicial arbitraria

    public synchronized void entrarSala() throws InterruptedException {
        while (personasEnSala >= CAPACIDAD_NORMAL || temperaturaActual > TEMPERATURA_UMBRAL) {
            wait();
        }
        personasEnSala++;
    }

    public synchronized void entrarSalaJubilado() throws InterruptedException {
        while (temperaturaActual > TEMPERATURA_UMBRAL) {
            wait();
        }
        personasEnSala++;
    }

    public synchronized void salirSala() {
        personasEnSala--;
        notifyAll();
    }

    public synchronized void notificarTemperatura(int temperatura) {
        temperaturaActual = temperatura;
        if (temperaturaActual > TEMPERATURA_UMBRAL && personasEnSala > CAPACIDAD_REDUCIDA) {
            notifyAll();
        }
    }
}
