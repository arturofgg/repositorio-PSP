package Principal;

public class Mesa 
{
	private boolean tenedores[];
	
	public Mesa() 
	{
		tenedores = new boolean[5];
		for(int i=0;i<5;i++)
			tenedores[i] = true;
	}

//	public synchronized void dameIzq(int numero) throws InterruptedException 
//	{
//		if(tenedores[numero]==true)
//		{
//			tenedores[numero]=false;
//		}else
//		{
//			wait();
//		}
//	}
//	
//	public synchronized void dameDcho(int numero) throws InterruptedException
//	{
//		if (numero==4)
//		{
//			numero = 0;
//		}else 
//			numero++;
//		
//		if (tenedores[numero]==true)
//		{
//			tenedores[numero]=false;
//		}else
//		{
//			wait();
//		}
//	}
	
	public synchronized void devolverTenedores(int numero)
	{
		tenedores[numero]=true;
		if (numero==4)
		{
			numero = 0;
		}else 
			numero++;
		tenedores[numero]=true;
		System.out.println("Filosofo "+numero+ " deja de comer");
		notifyAll();
	}
	
	public synchronized void dameTenedores(int numero) throws InterruptedException 
	{
		if(tenedores[numero]==true && tenedores[(numero+1)%5]==true)
		{
			tenedores[numero]=false;
			tenedores[(numero+1)%5]=false;
			System.out.println("Filosofo "+numero+ " comiendo");
		}else
		{
			wait();
		}
	}
}
