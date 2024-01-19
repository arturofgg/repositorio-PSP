package Principal;

public class Main {

	public static void main(String[] args) {
		Parking parking = new Parking();

        Vehiculo v[] = new Vehiculo[20];
        
        for (int i=0;i<10;i++)
		{
			v[i] = new Vehiculo(parking, i, 1);
			v[i].start();
		}
        
        for (int i=10;i<20;i++)
		{
			v[i] = new Vehiculo(parking, i, 2);
			v[i].start();
		}
        
	}

}
