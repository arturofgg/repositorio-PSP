package Principal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Cliente extends Thread 
{
	int id;
	String nombre;
	Socket socket;
	Scanner sc;
	InputStream FlujoDeEntrada;
	DataInputStream DatosEntrada;
	OutputStream FlujoDeSalida;
	PrintWriter DatosSalida;
	Contacto contacto;
	ObjectOutputStream ObjetosSalida;
	
	Timer timer;
	TimerTask timerTask;
	boolean fin;
	
	public Cliente () throws UnknownHostException, IOException
	{
		socket = new Socket ("192.168.20.99", 8000);
		FlujoDeEntrada = socket.getInputStream();
		FlujoDeSalida = socket.getOutputStream();
		DatosEntrada = new DataInputStream(FlujoDeEntrada);
		DatosSalida = new PrintWriter(FlujoDeSalida);
		sc = new Scanner (System.in);
		contacto = new Contacto("Pepe","García","666222333");
		ObjetosSalida = new ObjectOutputStream(FlujoDeSalida);
	}
	
	public void run()
	{
		fin=false;
		String comando;
		String destino;
		String mensaje;
		
		timerTask = new TimerTask ()
			{
			public void run()
			{
				try 
				{
					if (fin)
					{
						timer.cancel();
						timer.purge();
					}
					else
						LeerMensajeNoBloqueante();
				} catch (IOException e) {e.printStackTrace();}
			}
			
			};
		
		
		System.out.print("Introduzca nombre de usuario:");
		nombre = sc.next();
		try
		{
			EnviarMensaje(nombre);
			id = Integer.parseInt(LeerMensajeBloqueante());
			System.out.println("El identificador asignado es:"+id);
			menu();
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask,0, 1000);
			do
			{
				comando = sc.next();
				if (comando.compareTo("LIST")==0)
				{
					EnviarMensaje(comando);
				}
				else
					if (comando.compareTo("SEND")==0)
					{
						EnviarMensaje(comando);
						System.out.println("Destino:");
						destino = sc.next();
						EnviarMensaje(destino);
						System.out.println("Mensaje:");
						sc.nextLine();
						mensaje = sc.nextLine();
						EnviarMensaje(mensaje);
					}
					else
						if (comando.compareTo("FIN")==0)
						{
							EnviarMensaje(comando);
							fin=true;
						}
						else 
							if (comando.compareTo("SHARE")==0)
							{
								EnviarMensaje(comando);
								ObjetosSalida.writeObject(contacto);
								ObjetosSalida.flush();
							}
			} while (!fin);
			socket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void menu()
	{
		System.out.println("LIST  - Lista de usuarios");
		System.out.println("SEND  - Enviar Mensaje a un usuario");
		System.out.println("SHARE - Enviar tarjeta de visita");
		System.out.println("FIN   - Finalizar ejecución");
	}
	
	public void EnviarMensaje(String msg)
	{
		DatosSalida.print(msg);
		DatosSalida.flush();
	}
	
	public String LeerMensajeBloqueante() throws IOException
	{
		String msg;
		int BytesLeidos;
		byte [] Mensaje = new byte[80];

		do
		{System.out.println(" ");
		BytesLeidos = DatosEntrada.read(Mensaje);
		} while (BytesLeidos <1);
		msg = new String(Mensaje,0,BytesLeidos);
		return msg;
	}
	
	public void LeerMensajeNoBloqueante() throws IOException
	{
		int BytesLeidos=0;
		byte[] Mensaje = new byte[80];
		String msg;
		
		if (DatosEntrada.available()>0)
		   BytesLeidos = DatosEntrada.read(Mensaje);
		if (BytesLeidos>0)
		{
			msg = new String(Mensaje,0,BytesLeidos);
			System.out.println(msg);
		}
	}
}
