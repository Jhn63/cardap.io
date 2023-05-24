package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import server.Server;


public class DataServerRequestPatternTest {
    public static void main(String[] args) throws IOException {

        try {
            Socket s = new Socket(Server.HOST_STRING, Server.PORT);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String sendData = "";

            while (!sendData.equals("exit"))
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
            
        } catch (IOException e ) { System.out.println("connection failed");}
    }
}
