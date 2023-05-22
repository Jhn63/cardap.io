package client.hall;

import client.Protocol;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;



/**
 * Prototipo de cliente (app garçom)
 * será substituido posteriormente
 */
public class ClientPrototype {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4444);

        Scanner scan = new Scanner(System.in);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        for (int i = 0; i < 1000; i++)
        {
            out.writeUTF(String.format("%d,%d", Protocol.OPEN_TABLE, new Random(System.currentTimeMillis())));
            System.out.println(in.readUTF());
        }

        String msg = " ";
        
        scan.close();
        in.close();
        out.close();
        socket.close();
    }
}
