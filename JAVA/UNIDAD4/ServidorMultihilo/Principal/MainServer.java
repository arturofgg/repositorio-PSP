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
		ArrayList<Usuario> usuarios;
		int total_usuarios=0;
		boolean fin = false;
		
		usuarios = new ArrayList<Usuario>();
		SocketServidor = new ServerSocket(8000);
		System.out.println("Servidor disponible");
		
		while (!fin)
		{
			SocketCliente = SocketServidor.accept();

			usuario = new Usuario(SocketCliente, total_usuarios, usuarios);
			usuarios.add(usuario);
			usuario.start();
			total_usuarios++;
		}
		SocketServidor.close();
	}
}
