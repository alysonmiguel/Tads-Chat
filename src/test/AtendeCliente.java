package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtendeCliente extends Thread {
	Socket cliente;

	AtendeCliente(Socket cli) {
		this.cliente = cli;
	}

	@Override
	public void run() {

		try {
			System.out.println("Cliente conectado com thread (" + this.getId() + 
					") : " + cliente.getInetAddress());

			// Fluxo de dados do cliente para comunicar e enviar Objeto
			ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
			Mensagem objeto = (Mensagem) entrada.readObject();
			System.out.println(objeto.toString());

			ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
			saida.writeObject(objeto);

			cliente.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
