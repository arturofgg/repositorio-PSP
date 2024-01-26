package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Usuario extends Thread 
{
	private ColaTrabajos colaTrabajos;
	private Socket socket;
	private InputStream FlujoDeEntrada;
	private OutputStream FlujoDeSalida;
	private ObjectInputStream ObjetosEntrada;
	private ObjectOutputStream ObjetosSalida;
	private String nick;
	private String perfil;
	
	public Usuario (Socket socket,ColaTrabajos colaTrabajos) throws IOException
	{
		this.socket = socket;
		this.colaTrabajos = colaTrabajos;
		FlujoDeEntrada = socket.getInputStream();
		FlujoDeSalida = socket.getOutputStream();
		ObjetosEntrada = new ObjectInputStream(FlujoDeEntrada);
		ObjetosSalida = new ObjectOutputStream(FlujoDeSalida);
	}
	
	public void run()
	{
		Trabajo trabajo=null;
		Trabajo t=null;
		try
		{
			System.out.print("Se ha conectado ");
			
			do
			{
				trabajo = (Trabajo)ObjetosEntrada.readObject();
			} while (trabajo==null);
			
			nick = trabajo.getIdentificacion();
			perfil=(trabajo.getTipo()==0)?"Cliente":"Técnico";
			
			System.out.println(nick+"("+perfil+")");
			
			if (trabajo.getTipo()==0) // Si se trata de un "Cliente".
			{
				colaTrabajos.addTrabajo(trabajo); // Añadimos el trabajo a la cola (implica quedar dormidos).
				ObjetosSalida.writeObject(trabajo); // Cuando el trabajo ha sido resuelto se lo devolvemos al Cliente.
				ObjetosSalida.flush();
				colaTrabajos.deleteTrabajo(trabajo); // Eliminamos el trabajo de la cola de trabajos
			}
			else					// Si se trata de un "Técnico".
			{
				trabajo = colaTrabajos.getTrabajo(); // Recogemos una trabajo que esté pendiente (implica quedar dormidos si no hay)
				trabajo.setEstado("EN CURSO");  // Cambiamos el estado del trabajo
				t=trabajo; // Anotamos la referencia
				
				ObjetosSalida.writeObject(trabajo); // Enviamos el trabajo al técnico para que lo resuelva 
				ObjetosSalida.flush();
				do
				{
					trabajo = (Trabajo)ObjetosEntrada.readObject(); // Recibimos la solución del técnico
				} while (trabajo==null);
				
				t.setSolucion(trabajo.getSolucion());
				t.setEstado("FINALIZADO");
				colaTrabajos.finalizar();
			}
			ObjetosSalida.close();
			ObjetosEntrada.close();
			FlujoDeSalida.close();
			FlujoDeEntrada.close();
			socket.close();
			System.out.println("Se ha desconectado "+nick+"("+perfil+")");
		} catch (IOException | ClassNotFoundException | InterruptedException e) {e.printStackTrace();}
	}
}