package Principal;

public class Sala {
	
	private int numeroJugadores;
	private int nContrincante;
	private boolean rContrincante;
	private int nOrden;

	public Sala(int numeroJugadores, int nOrden) {
		this.numeroJugadores=numeroJugadores;
		this.nOrden=nOrden;
	}

	public synchronized boolean jugar(int numeroJugador) {
		if (nOrden==0)
		{
			nOrden++;
//			nContrincante = ((Jugador)Thread.currentThread()).getNumero();
			nContrincante = numeroJugador;
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nOrden=0;
				return rContrincante;
		}else
		{
			if(nOrden==1)
			{
				nOrden++;
				if (numeroJugador > nContrincante) 
				{
//					((Jugador)Thread.currentThread()).setNumero(numeroJugador-nContrincante);
					rContrincante = true;
					return false; //significa que sigue jugando
				}
				else if (numeroJugador<nContrincante) 
				{
					rContrincante = false;
					return true; // significa que pierde
				}
				else
				{
					rContrincante=true;
					return true;
				}
			}
			else
			{
				return false;
			}
		}
	}
}
