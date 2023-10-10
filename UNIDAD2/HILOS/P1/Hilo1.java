package HILOS.P1;
public class Hilo1 extends Thread {
	public Hilo1(String str) {super(str);}

	public void run() 
	{
		for (int i = 0; i < 10 ; i++)
			System.out.println(i + " " + getName());
		System.out.println("Termina el hilo " + getName());
	}

	public static void main(String[] args) 
	{
		new Hilo1("Hilo A").start();
		new Hilo1("Hilo B").start();
		System.out.println("Termina el hilo principal");
	}
	
}