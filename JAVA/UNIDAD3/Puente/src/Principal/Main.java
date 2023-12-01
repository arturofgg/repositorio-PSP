package Principal;

public class Main {

	public static void main(String[] args) {
		Vehiculo v1 = new Vehiculo(1, 0);
		Vehiculo v2 = new Vehiculo(2, 1);
		Vehiculo v3 = new Vehiculo(3, 1);
		Vehiculo v4 = new Vehiculo(4, 0);

		v1.start();
		v2.start();
		v3.start();
		v4.start();

	}

}
