package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(7000);
			System.out.println("Servidor iniciado...");
			
			while(true) {
				AtendeCliente ac = new AtendeCliente(servidor.accept());
				ac.start();
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro!");
		}
		
		

	}

}
