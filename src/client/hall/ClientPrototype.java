package client.hall;

import client.tools.Product;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Prototipo de cliente (app garçom)
 * será substituido posteriormente
 */
public class ClientPrototype {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 4444);

        Scanner scanner = new Scanner(System.in);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String msg = " ";
        while(!(msg.equals("close"))) {
            msg = scanner.nextLine();
            out.writeUTF(msg);

            try {
                //deserializando objeto
                Product product = (Product) in.readObject();
                System.out.println(product.getName());
            } catch (Exception ignored) { }
        }

        in.close();
        out.close();
        socket.close();
    }
}
