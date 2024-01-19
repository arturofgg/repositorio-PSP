package Principal;

import java.util.concurrent.Semaphore;

public class Parking {
	private  int CapacidadTotal = 20;
    private  int capacidadActual = 0;
	private static Semaphore semaforoA = new Semaphore(1);
	private static Semaphore semaforoB = new Semaphore(1);
    private static Semaphore mutex = new Semaphore(1);
    //private static int contA = 0;
    //private static int contB = 0;
    private static int politica =0 ; // si es 0 es libre, si es 1 es alterna

    public synchronized void vehiculoEnAccesoA(int id) throws InterruptedException
	{
        try 
		{
			mutex.acquire();
			actualizaRestricciones();
			if (politica==1)
			{
				semaforoA.acquire();
				capacidadActual++;
				semaforoB.release();
				
			}else {
				capacidadActual++;
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
    	 try 
 		{
 			mutex.acquire();
 			actualizaRestricciones();
 			if (politica==1) 
 			{
 				semaforoB.acquire();
 				capacidadActual++;
 				semaforoA.release();
 				
 			}else{
 				capacidadActual++;
 			}
 		}
 		catch (InterruptedException e) {e.printStackTrace();}
 		finally 
 		{
 			mutex.release();
 		}
 	}

    public synchronized void vehiculoAbandonaAparcamiento(int id)
	{
    	notifyAll();
    	actualizaRestricciones();
    	capacidadActual--;
	}

    public synchronized void actualizaRestricciones() 
	{
    	if (capacidadActual>(CapacidadTotal-(CapacidadTotal*10/100))) 
		{
    		politica = 1;
		}
    	
    	else if (capacidadActual<(CapacidadTotal-(CapacidadTotal*25/100))) 
		{
    		politica = 1;
		}
    	
    	else politica = 0;
	}
}
