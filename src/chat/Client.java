package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Socket conexao;
    private final String HOST = "127.0.0.1";
    private final int PORT = 7000;
    private Scanner scanner;
    private ClientSocket clientSocket;

    public Client() {
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        try{
            conexao = new Socket(HOST, PORT);
            clientSocket = new ClientSocket(conexao);
            new Thread(this).start(); // cria a thread
            messageLoop();
        }finally {
            conexao.close();
        }
    }

    @Override
    public void run() {
        String msg;
        while ((msg = clientSocket.getMessage()) != null) { // recebe as msg do servidor em um thread separada / pra nao ter bloqueios
            System.out.println(msg);
        }
    }

    private void messageLoop() throws IOException {
        String msg;
        System.out.print("Digite sua mensagem ou /exit para sair quando desejar) \n");
        do {
            msg = scanner.nextLine(); // le a msg do cliente
            clientSocket.sendMsg(msg); //
        } while (!msg.equalsIgnoreCase("/exit"));
    }
    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro no cliente" + e.getMessage());
        }
        System.out.println("Cliente finalizado");
    }
}
