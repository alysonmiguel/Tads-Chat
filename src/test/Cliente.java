package test;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		
		try {
		//Declarar o socket cliente
		Socket cliente = new Socket("127.0.0.1", 7000);		
		System.out.println("Cliente iniciado");

		Mensagem msg = new Mensagem("Alyson", "Oi teste!");
		//Fluxo de dados de envio
		ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
		System.out.println("Antes do server: " + msg.toString());		
		saida.writeObject(msg);
		
		//
		ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
		Mensagem obj = (Mensagem) entrada.readObject();
		System.out.println("Depois do server: " + obj.toString());
		
		cliente.close();
		
		System.out.println("Cliente finalizado");
		
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a conexao");
		}
		 

	}

}
