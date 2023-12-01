package Principal;

import java.util.concurrent.Semaphore;

public class Jugador extends Thread{

	private int n;
	private boolean seguir_jugando;
	private static int nOrden=0;
	private static int nOponente1;
	private static boolean ganarOponente1;
	private static Semaphore mutex = new Semaphore(1);
	private static Semaphore dormir = new Semaphore(0);
	public static int numeroJugadores;
	private static Semaphore dormir2 = new Semaphore(0);
	
	public Jugador( int n, int nj) {
		numeroJugadores=nj;
		this.n=n;
		this.seguir_jugando=true; 
	}
	public void run()
	{
		int numeroOrden = 0;
		while ( seguir_jugando==true)
		{
			try {
				mutex.acquire();
				nOrden++;
				numeroOrden=nOrden;
			} catch (InterruptedException e) {e.printStackTrace();}
			finally {mutex.release();}
			
			if(numeroOrden==1) {
				System.out.println("Jugador "+n+" jugando como jugador 1");
				nOponente1 = n;
				dormir2.release();
				try {
					dormir.acquire();
					mutex.acquire();
					nOrden=0;
				} catch (InterruptedException e) {e.printStackTrace();}
				finally {mutex.release();}
				seguir_jugando = ganarOponente1;
			}
			else if(numeroOrden==2) {
				System.out.println("Jugador "+n+" jugando como jugador 2");
				numeroJugadores--;
				try {
					dormir2.acquire();
				} catch (InterruptedException e) {e.printStackTrace();}
				if (n>nOponente1) {
					seguir_jugando=true;
					ganarOponente1=false;
				}
				else if(n<nOponente1) {
					seguir_jugando=false;
					ganarOponente1=true;
				}
				else {
					seguir_jugando=false;
					ganarOponente1=false;
					numeroJugadores--;
				}
			dormir.release();
			}
			if (numeroJugadores<2) {
				if(numeroJugadores==1) {
					System.out.println("Ha ganado el "+n);
				}
				seguir_jugando=false;
			}
		}
	}
}
