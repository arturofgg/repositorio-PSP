package HILOS.P2;

public class Caballo extends Thread{
    public Caballo(String str) {super(str);}

    public void run(){
        System.out.println("Soy el caballo "+getName());
        for(int z=0; z<10; z++){
            System.out.println(getName()+" "+z);
            try{
                sleep((long) (Math.random()*1000));
            }catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}

//hola