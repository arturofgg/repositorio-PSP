package Principal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class AtenderCliente extends Thread 
{
	private Socket socketcliente;
	private InputStream FlujoDeEntrada;
	private DataInputStream DatosEntrada;
	
	public AtenderCliente (Socket socketcliente)
	{
		this.socketcliente = socketcliente;
	}
	
	public void run()
	{
		String msg;
		int BytesLeidos;
		byte [] Mensaje = new byte[80];
		
		try 
		{
			FlujoDeEntrada = socketcliente.getInputStream();
			DatosEntrada = new DataInputStream(FlujoDeEntrada);
		
			do
			{
				do
				{
					BytesLeidos = DatosEntrada.read(Mensaje);
				} while (BytesLeidos <1);
				msg = new String(Mensaje,0,BytesLeidos);
				System.out.println(msg);
			} while (msg.compareTo("FIN")!=0);
			socketcliente.close();
			
		} catch (IOException e) {e.printStackTrace();}
	}
}
