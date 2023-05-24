package client.kitchen;

import client.tools.Product;
import client.tools.Table;

import java.io.*;
import java.net.Socket;

public class Order implements Runnable {
    private  final Socket socket;
    private Table localTable;

    public Order(Socket socket) {
        this.socket = socket;
        this.localTable = null;
    }

    public void run () {
        try {
            boolean received = false;
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            localTable = (Table) in.readObject();

            while (!received) {
                for (Product p : localTable.getRequests()) {
                    int textSize = 30;

                    int points = (textSize - (Integer.toString(p.getQuantity()).length() + p.getName().length())) - 2;
                    String sb = p.getName() +
                            " " +
                            ".".repeat(Math.max(0, points)) +
                            " " +
                            p.getQuantity();

                    System.out.println(sb);
                }
                System.out.printf("Mesa: %s", localTable.getID());

                if (received) {
                    out.writeUTF("OK");
                    break;
                }
            }

            // CRIAR UM XML APÓS RECEBIMENTO

            in.close();
            out.close();
            socket.close();
        } catch (IOException | ClassNotFoundException ignored) { }
    }
}
