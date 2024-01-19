package JAVA.UNIDAD3.zParking;

public class Parking {
    private int capacidad = 20;
    private static Semaphore mutex = new Semaphore(1);


    public Parking(int capacidad)
	{
		this.capacidad = capacidad;

	}

    public synchronized void vehiculoEnAccesoA(int id) throws InterruptedException
	{
        try 
		{
			mutex.acquire();
			if (hayVehiculos==false) 
			{
				contadorVehiculos++;
				hayVehiculos = true;
			}
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		finally 
		{
			mutex.release();
		}
	}
	
    public synchronized void vehiculoEnAccesoB(int id) throws InterruptedException
	{

	}

    public synchronized void vehiculoAbandonaAparcamiento(int id) throws InterruptedException
	{

	}

    public synchronized void actualizaRestricciones(int id) throws InterruptedException
	{

	}
}
