package HILOS.P2;
public class Main extends Thread{
    public static void main(String[] args){
        Caballo[] caballos = new Caballo[10];
        
        for(int z=0; z<10; z++){
            caballos[z] = new Caballo("C"+ z);
            caballos[z].start();
        }
    }
}