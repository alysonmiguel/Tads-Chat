package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Socket socket;
    private final String HOST = "127.0.0.1";
    private final int PORT = 7000;
    private Scanner scanner;
    private ClientSocket clientSocket;

    public Client() {
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        try{
            socket = new Socket(HOST, PORT);
            clientSocket = new ClientSocket(socket);
            System.out.println("Cliente conectado ao servidor");
            new Thread(this).start();
            messageLoop();
        }finally {
            socket.close();
        }
    }

    @Override
    public void run() {
        String msg;
        while ((msg = clientSocket.getMessage()) != null) {
            System.out.println("Mensagem recebida do servidor " + msg);
        }
    }

    private void messageLoop() throws IOException {
        String msg;
        do {
            System.out.println("Digite uma mensagem (ou /exit para sair)");
            msg = scanner.nextLine();
            clientSocket.sendMsg(msg);
            System.out.println("Mensagem recebida" + clientSocket.getMessage());
        } while (!msg.equalsIgnoreCase("sair"));
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
