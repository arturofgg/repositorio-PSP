package Principal;

import java.io.Serializable;

public class Contacto implements Serializable 
{
	private String nombre;
	private String apellidos;
	private String telefono;
	
	public Contacto (String n, String a,String t)
	{
		nombre = n;
		apellidos = a;
		telefono = t;
	}
	
	public String toString()
	{
		return (nombre+" "+apellidos+" "+telefono);
	}
}
