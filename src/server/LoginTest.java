package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args) throws IOException {

        try {
            Socket s = new Socket(Server.HOST_NAME, Server.PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String sendData = "";

            while (s.isConnected())
            {
                System.out.print("> ");
                sendData = scan.nextLine();
                out.writeUTF(sendData);
                System.out.println(in.readUTF());
            }

            scan.close();
            s.close();
            in.close();
            out.close();
        } catch (IOException e ) { e.printStackTrace();}
    }
}
