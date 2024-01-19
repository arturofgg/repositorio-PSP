package Principal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket socket;
		OutputStream FlujoDeSalida;
		PrintWriter DatosSalida;
		Scanner sc;
		String mensaje;
		socket = new Socket("192.168.20.99",9000);
		FlujoDeSalida=socket.getOutputStream();
		DatosSalida = new PrintWriter(FlujoDeSalida);
		System.out.println("CLIENTE");
		sc = new Scanner(System.in);
		do {
			mensaje= sc.nextLine();
			DatosSalida.print(mensaje);
			DatosSalida.flush();
		} while (mensaje.compareTo("FIN")!=0);
		socket.close();
		sc.close();
	}
}
