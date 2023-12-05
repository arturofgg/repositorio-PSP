public class MedidorTemperatura extends Thread {
    private final GestorSala gestor;

    public MedidorTemperatura(GestorSala gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        while (true) {
            // Simular la medición de temperatura
            int temperatura = simularMedicion();

            // Notificar al gestor
            gestor.notificarTemperatura(temperatura);

            // Esperar un tiempo antes de la próxima medición
            try {
                Thread.sleep(1000); // por ejemplo, espera 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int simularMedicion() {
        // Simular una medición de temperatura
        // Puedes generar un valor aleatorio o utilizar algún otro método de simulación
        return (int) (Math.random() * 50) + 20; // Por ejemplo, un valor aleatorio entre 20 y 70
    }
}
