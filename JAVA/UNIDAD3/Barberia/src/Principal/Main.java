package Principal;

import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Barberia Barberia = new Barberia(4);
		Cliente clientes[] = new Cliente[10];
		Barbero barbero = new Barbero(Barberia);
		
		barbero.start();
		for(int i=0; i<10; i++)
		{
			clientes[i] = new Cliente(i,Barberia);
			clientes[i].start();
		}
	}
}
