package server;

import tools.Product;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection implements Runnable{
    Socket socket;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            String clientName = Thread.currentThread().getName();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String msg;
            while (true) {
                msg = in.readUTF();
                if (msg.equals("close")) { break; }

                //prototipo (mudar para uma data base)
                if (msg.equals("1")) {
                    Product product = new Product("Coca-cola 1l", 1001, 3.5F, null);
                    out.writeObject(product);   // enviando objeto serializado
                } else {
                    System.out.println(clientName + ": request not found");
                    out.writeObject(null);
                }
            }
            System.out.println("process with "+ clientName + " finished");
            in.close();
            out.close();
            socket.close();
        } catch (IOException ignored) { }
    }
}
