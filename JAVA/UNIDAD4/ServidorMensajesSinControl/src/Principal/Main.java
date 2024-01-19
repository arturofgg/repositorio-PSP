package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main 
{

	public static void main(String[] args) 
	{
		ServerSocket SocketServidor;
		Socket SocketCliente;
		boolean fin = false;
		AtenderCliente micliente;
	
		try 
		{
			SocketServidor = new ServerSocket(9000);
			while (!fin)
			{
				SocketCliente = SocketServidor.accept();
				micliente = new AtenderCliente(SocketCliente);
				micliente.start();
			}
		} catch (IOException e) {e.printStackTrace();}
	}

}
