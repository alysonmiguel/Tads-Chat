import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket udpsocket = new DatagramSocket(6789);
        byte[] buffer = new byte[1000];
        while (true){
            DatagramPacket recebe = new DatagramPacket(buffer, buffer.length);
            udpsocket.receive(recebe);
            byte[] bufferrecebido = recebe.getData();
            InetAddress ip = recebe.getAddress();
            int port = recebe.getPort();
            System.out.println("Recebeu: " + new String (bufferrecebido, StandardCharsets.UTF_8));
            String saida = new String (bufferrecebido, StandardCharsets.UTF_8);
            saida = saida.toUpperCase();
            DatagramPacket responde = new DatagramPacket (saida.getBytes(), saida.getBytes().length, ip, port);
            udpsocket.send(responde);
        }
    }
}