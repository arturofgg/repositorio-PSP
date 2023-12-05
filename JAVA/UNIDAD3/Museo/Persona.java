public class Persona extends Thread {
    private final GestorSala gestor;
    private final boolean esJubilado;

    public Persona(GestorSala gestor, boolean esJubilado) {
        this.gestor = gestor;
        this.esJubilado = esJubilado;
    }

    @Override
    public void run() {
        try {
            if (esJubilado) {
                gestor.entrarSalaJubilado();
            } else {
                gestor.entrarSala();
            }

            // Realizar actividades en la sala

            gestor.salirSala();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
