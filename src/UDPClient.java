import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        if (args.length != 3){
            System.out.println("Uso correto UDPClient <servidor> <porta> <dados>");
            System.exit(0);
        }
        DatagramSocket udpclient = new DatagramSocket();
        InetAddress server = InetAddress.getByName(args[0]);
        int porta= Integer.parseInt(args[1]);
        byte[] dados = args[2].getBytes();
        DatagramPacket pedido = new DatagramPacket(dados, dados.length, server, porta);
        udpclient.send(pedido);
        byte[] buffer = new byte[dados.length];//suficiente para a palavra hello
        DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
        udpclient.receive(resposta);
        String sdados = new String (resposta.getData(), StandardCharsets.UTF_8);
        System.out.println("Nova String:" + sdados);
        udpclient.close();
    }
}
