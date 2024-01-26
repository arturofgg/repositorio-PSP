package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer 
{
	public static void main(String[] args) throws IOException 
	{
		ServerSocket SocketServidor;
		Socket SocketCliente;
		
		Usuario usuario;
		ColaTrabajos colaTrabajos = new ColaTrabajos();
		
		boolean fin = false;

		SocketServidor = new ServerSocket(8000);
		System.out.println("Servidor HelpDesk disponible");

		while (!fin)
		{
			SocketCliente = SocketServidor.accept();

			usuario = new Usuario(SocketCliente,colaTrabajos);
			usuario.start();
		}
		SocketServidor.close();
	}
}

