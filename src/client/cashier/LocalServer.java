package client.cashier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalServer extends Thread{
    private ServerSocket server;
    private DataBase base;

    public LocalServer() {
        this.base = new DataBase();
    }

    public DataBase getBase() {
        return base;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(4444);
            ExecutorService e = Executors.newFixedThreadPool(5);
            while (true) {
                Socket socket = server.accept();
                WaiterConnection connection = new WaiterConnection(socket, base);
                e.execute(connection);
            }
            //e.shutdown();
            //local.server.close();
        } catch (IOException ignored) {}
    }

}
