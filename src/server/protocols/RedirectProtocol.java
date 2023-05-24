package server.protocols;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import server.ClientPrototype;

public class RedirectProtocol implements Runnable, Protocol
{
    public final Socket socket;
    public HashMap<Integer, ClientPrototype> clientDB;
    public ClientPrototype loggedClient;
    
    public RedirectProtocol (Socket socket, HashMap<Integer, ClientPrototype> clientDB)
    {
        this.socket = socket;
        this.clientDB = clientDB;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream in  = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("connection established\t" + socket.getRemoteSocketAddress().toString());

            Executor pool = Executors.newFixedThreadPool(4);

            while (!socket.isClosed())
            {
                String[] request = in.readUTF().split(",");
             
                switch (request[0])
                {
                    case LOGIN: 
                        pool.execute(new LoginProtocol(in, out, request, loggedClient, clientDB));
                        break;
                    case REGISTRATION:
                        pool.execute(new RegisterProtocol(in, out, request, loggedClient, clientDB));
                        break;
                    case CONSULT:
                        out.writeUTF(consult(request));
                        break;
                    case SYNCHRONIZATION:
                        out.writeUTF(synchData(request));
                        break;
                    default:
                        out.writeUTF(FORBIDDEN_REQUEST);
                        break;
                }
            }

            in.close();
            out.close();
        
        } catch (Exception e) { 
            System.out.println("disconnected\t\t" + socket.getRemoteSocketAddress().toString());
        }
    }

    public String consult (String[] request)
    {
        if (loggedClient == null) 
            return FORBIDDEN_REQUEST;
            
        String response = CONSULT_NOT_FOUND;
        
        try 
        {
            // String clientName = request[1];
            int clientID = Integer.parseInt(request[2]);
            // String date = request[3]; // ano-mes-dia
            response = clientDB.get(clientID).data.toString();
            
        } catch (Exception e) { return FORBIDDEN_REQUEST; }

        return response;
    }

    public String synchData (String[] data)
    {
        return "";
    }

}