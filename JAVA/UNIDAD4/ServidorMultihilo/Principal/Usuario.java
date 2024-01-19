package Principal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Usuario extends Thread 
{
	private int id;
	private String nombre;
	private Socket socket;
	private ArrayList<Usuario> usuarios;
	
	private InputStream FlujoDeEntrada;
	private DataInputStream DatosEntrada;
	private OutputStream FlujoDeSalida;
	private PrintWriter DatosSalida;
	
	private ObjectInputStream ObjetosEntrada;
	//private ObjectOutputStream ObjetosSalida;
	
	public Usuario (Socket socket,int id,ArrayList<Usuario> usuarios)
	{
		this.socket = socket;
		this.id = id;
		this.usuarios = usuarios;
	}
	
	public void run()
	{
		String comando;
		Contacto contacto;
		boolean fin=false;
		try 
		{
			FlujoDeEntrada = socket.getInputStream();
			FlujoDeSalida = socket.getOutputStream();
			DatosEntrada = new DataInputStream(FlujoDeEntrada);
			DatosSalida = new PrintWriter(FlujoDeSalida);
			
			ObjetosEntrada = new ObjectInputStream(FlujoDeEntrada);
			//ObjetosSalida = new ObjectOutputStream(FlujoDeSalida);
			
			
			//Protocolo
			// 1º El servidor recibe el nombre del cliente.
			// 2º El servidor responde con el identificador
			// 3º El servidor queda a la espera de comandos del cliente.
			nombre = LeerMensajeBloqueante();
			System.out.println("El servidor recibe la conexión de "+nombre);
			EnviarMensaje(String.valueOf(id));
			while (!fin)
			{
				comando = LeerMensajeBloqueante();
				// En función del Mensaje actúo
				if (comando.compareTo("LIST")==0)
					EnviarListaUsuarios();
				else
					if (comando.compareTo("SEND")==0)
						ComunicarMensaje();
					else
						if (comando.compareTo("FIN")==0)
						{
							System.out.println("El usuario "+nombre+" se desconecta");
							usuarios.remove(this);
							fin=true;
						}
						else
							if (comando.compareTo("SHARE")==0)
							{
								try {
									contacto = (Contacto)ObjetosEntrada.readObject();
									System.out.println("He recibido "+contacto);
								} catch (ClassNotFoundException e) {e.printStackTrace();}
							}
			}
			socket.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void EnviarListaUsuarios()
	{
		String lista="";
		for (Usuario u :usuarios)
			lista=lista.concat(u.id+" "+u.nombre+"\n");
		EnviarMensaje(lista);
	}
	
	public void ComunicarMensaje() throws IOException
	{
		String destino;
		String mensaje;
		Usuario u;
		
		destino = LeerMensajeBloqueante();
		mensaje = LeerMensajeBloqueante();
		u = BuscarUsuarioPorNombre(destino);
		if (u!=null)
			u.EnviarMensaje(nombre+" dice: "+mensaje);
		
	}
	
	public Usuario BuscarUsuarioPorNombre (String name)
	{
		for (Usuario u : usuarios)
		{
			if (u.nombre.compareTo(name)==0)
				return u;
		}
		return null;
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
		{
		BytesLeidos = DatosEntrada.read(Mensaje);
		} while (BytesLeidos <1);
		msg = new String(Mensaje,0,BytesLeidos);
		return msg;
	}
	

}
