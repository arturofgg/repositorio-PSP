package Principal;

import java.util.ArrayList;

public class ColaTrabajos 
{
	private ArrayList<Trabajo> trabajos;
	
	public ColaTrabajos ()
	{
		trabajos = new ArrayList<Trabajo>();
	}
	
	public synchronized void addTrabajo (Trabajo trabajo) throws InterruptedException
	{
		trabajo.setEstado("PENDIENTE"); // Se establece el trabajo como Pendiente
		trabajos.add(trabajo);			// Se añade el trabajo al Arraylist
		notifyAll();					// Se despiertan a los hilos dormidos por si hay técnicos disponibles.
		while (trabajo.getEstado().compareTo("FINALIZADO")!=0) // El hilo del cliente se duerme mientras su trabajo no esté finalizado
			wait();
	}
	
	public synchronized Trabajo getTrabajo() throws InterruptedException
	{
		Trabajo trabajo = null;
		while (trabajo==null)
		{
			for (Trabajo t: trabajos)	// Se recorre la lista de trabajos en busca de uno PENDIENTE
			{
				if (t.getEstado().compareTo("PENDIENTE")==0)
					trabajo = t;		// Si se encuentra se registra su referencia
			}
			if (trabajo==null)	// Si la referencia es null significa que no se ha encontrado trabajos pendientes por lo que se duerme
				wait();
		}
		return trabajo;
	}
	
	public synchronized void deleteTrabajo(Trabajo trabajo)
	{
		trabajos.remove(trabajo);	// Elimina el trabajo del arrayList
	}
	
	public synchronized void finalizar() // Se invoca cuando un técnico finaliza un trabajo
	{
		notifyAll();			// Despierta a los hilos que estén dormidos. 
	}
}
