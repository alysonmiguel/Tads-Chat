package chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClientSocket {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    private String nome;

    public ClientSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Cliente conectou ao servidor "+ socket.getRemoteSocketAddress());
    }

    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public String getMessage(){
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError();
    }

    public void close(){
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Erro close");
        }
    }
}
