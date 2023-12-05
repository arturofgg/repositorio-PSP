public class Main {
    public static void main(String[] args) {
        GestorSala gestorSala = new GestorSala();
        MedidorTemperatura medidor = new MedidorTemperatura(gestorSala);

        // Crear algunas personas
        Persona persona1 = new Persona(gestorSala, false);
        Persona persona2 = new Persona(gestorSala, true);

        // Iniciar hilos
        medidor.start();
        persona1.start();
        persona2.start();
    }
}
