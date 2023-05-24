package client.kitchen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KitchenServer {
    public static void main(String[] args) throws IOException {
        boolean open = true;
        ExecutorService e = Executors.newFixedThreadPool(1);
        ServerSocket socket = new ServerSocket(1010);

        while (open) {
            Socket ns = socket.accept();
            e.execute(new Order(ns));
        }
        socket.close();
    }
}

