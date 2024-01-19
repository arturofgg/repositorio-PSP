package Principal;

public class Vehiculo extends Thread{
	private Parking parking;
	private int id;
	private int acesso; //Si el acesso es el A acesso=1, si no acesso=2;
	
	public Vehiculo(Parking parking, int id, int acesso){
		this.parking = parking;
        this.id = id;
        this.acesso = acesso;
	}

	public void run() {
		try {
			//entra por A
            if (acesso==1)  {  
                parking.vehiculoEnAccesoA(id);
                System.out.println("vehiculo "+id+" entro al parking por la zona A");
                
            } else if (acesso==2){
            	parking.vehiculoEnAccesoB(id);
            	System.out.println("vehiculo "+id+" entro al parking por la zona B");
            }
            
            sleep((int)Math.random() * 500);
            parking.vehiculoAbandonaAparcamiento(id);
            System.out.println("vehiculo "+id+" salio del parking");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
