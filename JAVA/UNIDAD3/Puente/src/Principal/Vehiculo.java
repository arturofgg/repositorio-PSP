package Principal;

import java.util.concurrent.Semaphore;

public class Vehiculo extends Thread 
{
	private static int contadorVehiculos=0;
	private static boolean hayVehiculos=false;
	private static Semaphore semaforoIzqDcha = new Semaphore(1);
	private static Semaphore semaforoDchaIzq = new Semaphore(1);
	private static Semaphore mutex = new Semaphore(1);
	private static Semaphore semaforoCupoIzq = new Semaphore(3);
	private static Semaphore semaforoCupoDcha = new Semaphore(3);

	private int sentido; // 0-> Izq a Dcha y 1 ->Dcha a Izq
	public Vehiculo(int identificador, int sentido)
	{
		super();
		this.sentido = sentido;
	}

	public void VehiculoIzqDcha()
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
		
		try 
		{
			semaforoIzqDcha.acquire();
			contadorVehiculos++;
			
		} catch (InterruptedException e) {e.printStackTrace();}
		finally
		{
			semaforoIzqDcha.release();
		}
		
		try {
			semaforoCupoIzq.acquire();
			sleep((long) (Math.random() * 5000));
		} catch (InterruptedException e) {e.printStackTrace();}
		finally
		{
			semaforoCupoIzq.release();
		}
		

		try 
		{
			mutex.acquire();
			contadorVehiculos--;
			if (contadorVehiculos==0)
			{
				hayVehiculos=false;
				semaforoIzqDcha.release();
			}
		} catch (InterruptedException e) {e.printStackTrace();}
		finally 
		{
			mutex.release();
		}
	}
	
	
	public void VehiculoDchaIzq()
	{
		try 
		{
			mutex.acquire();
			if (hayVehiculos==false) 
			{
				semaforoIzqDcha.acquire();
				hayVehiculos=true;
			}
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		finally 
		{
			mutex.release();
		}
		
		try 
		{
			semaforoDchaIzq.acquire();
			contadorVehiculos++;
		} catch (InterruptedException e) {e.printStackTrace();}
		finally
		{
			semaforoDchaIzq.release();
		}
		
		try {
			semaforoCupoDcha.acquire();
			sleep((long) (Math.random() * 5000));
		} catch (InterruptedException e) {e.printStackTrace();}
		finally
		{
			semaforoCupoDcha.release();
		}
		

		try 
		{
			mutex.acquire();
			contadorVehiculos--;
			if (contadorVehiculos==0)
			{
				hayVehiculos=false;
				semaforoDchaIzq.release();
			}
		} catch (InterruptedException e) {e.printStackTrace();}
		finally 
		{
			mutex.release();
		}
	}
	
	
	public void run() 
	{
		switch (sentido)
		{
		case 0: VehiculoIzqDcha(); break;
		case 1: VehiculoDchaIzq(); break;
		default: System.out.println("ERROR FATAL -> Vehiculo con sentido inseperado");
		}
	}
}
