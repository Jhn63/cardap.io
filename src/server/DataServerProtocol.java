package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class DataServerProtocol implements Runnable, Protocol
{
    public final Socket socket;

    public DataServerProtocol (Socket newSocket)
    {
        socket = newSocket;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream in  = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String[] msg = in.readUTF().split(",");
            int protocol = Integer.parseInt(msg[0]);

            switch (protocol)
            {
                case LOGIN:
                    out.writeUTF("Login");
                    System.out.println("Login");
                    break;
                case REGISTRATION:
                    out.writeUTF("Register");
                    System.out.println("Register");
                    break;
                case CONSULT:
                    out.writeUTF("Consult");
                    System.out.println("Consult");
                    break;
                case SYNCHRONIZATION:
                    out.writeUTF("Syncrhonization");
                    System.out.println("Syncrhonization");
                    break;
                default:
                    out.writeUTF("Nothing");
                    System.out.println("Nothing");
                    break;
            }

            in.close();
            out.close();
        
        } catch (Exception e) { }
    }
}
