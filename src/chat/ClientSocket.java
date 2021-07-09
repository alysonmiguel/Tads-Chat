package chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClientSocket {

    private final Socket conexao;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientSocket(Socket conexao) throws IOException { // construtor para instanciar a classe e armazenar o socket
        this.conexao = conexao;
        this.in = new BufferedReader(new InputStreamReader(conexao.getInputStream())); //servidor
        this.out = new PrintWriter(conexao.getOutputStream(), true); // cliente
        System.out.println("Cliente conectou ao servidor "+ conexao.getRemoteSocketAddress());
    }

    public SocketAddress getRemoteSocketAddress(){ return conexao.getRemoteSocketAddress(); } // retorna a identificação do usuário (ip)

    public String getMessage(){
        try {
            return in.readLine(); // método de leitura da msg
        } catch (IOException e) {
            return null;
        }
    }

    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError(); // método de confirmação de envio da msg
    }

    public void close(){ //fecha todos os canais abertos
        try {
            in.close();
            out.close();
            conexao.close();
        } catch (IOException e) {
            System.out.println("Erro close");
        }
    }
}
