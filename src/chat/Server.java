package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private final int PORT = 7000;
    private ServerSocket serverSocket;
    private final List<ClientSocket> clients = new LinkedList<>(); // lista para add vários clientes

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciando" + PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException {
        while (true) {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept()); //retorna o socket
            clients.add(clientSocket);
            sendMsgToAll(clientSocket, "Entrou no chat");
            new Thread(() -> clientMessageLoop(clientSocket)).start(); 
//            System.out.println("Mensagem recebida do cliente" + clientSocket.getRemoteSocketAddress() + ": " + clientSocket.getMessage());
        }
    }

    private void clientMessageLoop(ClientSocket clientSocket) {
        String msg;
        try {
            while ((msg = clientSocket.getMessage()) != null) { // atribui o valor a variável msg e tbm verifica se o valor n foi lido -- null
                if ("/exit".equalsIgnoreCase(msg)) // verifica se o cliente quer finalizar a sessão
                    return;
                System.out.println("Msg recebida do cliente " + clientSocket.getRemoteSocketAddress() + " : " + msg);
                sendMsgToAll(clientSocket, msg); //envia a msg para todos os clientes ativos
            }
        } finally {
            clientSocket.close(); // garante que todos os canais sejam fechados
        }
    }

    private void sendMsgToAll(ClientSocket sender, String msg) {
        Iterator<ClientSocket> iterator = clients.iterator(); // percorre enquanto tiver elementos
        while (iterator.hasNext()) {
            ClientSocket clientSocket = iterator.next(); // obtem os elementos
            if (!sender.equals(clientSocket)) { // evita q a msg seja enviada para o remetente
                if (!clientSocket.sendMsg("["+ sender.getRemoteSocketAddress() + "]"+ " = " + msg)) {
                    iterator.remove(); // remove se nao foi possível enviar a msg
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (IOException e) {
            System.out.println("Erro no servidor :)" + e.getMessage());
        }
        System.out.println("Servidor Parou");
    }
}
