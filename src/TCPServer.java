import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TCPServer {
    public static void main(String argv[]) throws Exception{
        String palavra;
        String novaPalavra;
        ServerSocket socket = new ServerSocket(6789);
        while(true){
            Socket conexao = socket.accept();
            DataInputStream recebe = new DataInputStream (conexao.getInputStream());
            DataOutputStream responde = new DataOutputStream(conexao.getOutputStream());
            palavra = recebe.readUTF();
            System.out.println("Recebeu: " + palavra);
            novaPalavra = palavra.toUpperCase();
            responde.writeUTF(novaPalavra);
        }
    }
}