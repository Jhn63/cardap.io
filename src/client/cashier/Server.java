package client.cashier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
    private ServerSocket server;

    public Server() { }

    @Override
    public void run() {
        try {
            server = new ServerSocket(4444);
            ExecutorService e = Executors.newFixedThreadPool(5);
            while (true) {
                Socket socket = server.accept();
                ClientConnection connection = new ClientConnection(socket);
                e.execute(connection);
            }
            //e.shutdown();
            //local.server.close();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
