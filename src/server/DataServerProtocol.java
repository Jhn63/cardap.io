package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class DataServerProtocol implements Runnable, Protocol
{
    public final Socket socket;
    public HashMap<Integer, ClientPrototype> clientlist;
    public ClientPrototype connectedClient; 
    
    public DataServerProtocol (Socket newSocket, HashMap<Integer, ClientPrototype> clientlist)
    {
        socket = newSocket;
        this.clientlist = clientlist;
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream in  = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("conenctado");

            while (socket.isConnected())
            {
                String[] data = in.readUTF().split(",");
             
                switch (data[0])
                {
                    case LOGIN: 
                    { 
                        out.writeUTF(login(data));
                        if (connectedClient != null)
                            System.out.println(connectedClient.name + "@" + connectedClient.id + "fez login!");
                        break;
                    }
                    case REGISTRATION:
                    {
                        out.writeUTF(register(data));
                        break;
                    }
                    case CONSULT:
                    {
                        consult(data);
                        break;
                    }
                    case SYNCHRONIZATION:
                    {
                        sync(data);
                        break;
                    }
                    default:
                    {
                        out.writeUTF(FORBIDDEN_REQUEST);
                    }
                    break;
                }
            }

            in.close();
            out.close();

            System.out.println("desconnectado!");
        
        } catch (Exception e) { e.printStackTrace(); }
    }

    public String login (String[] data)
    {
        try  
        {
            int clientId = Integer.parseInt(data[2]);
            connectedClient = clientlist.get(clientId);
            
            if (connectedClient == null)
                return ACCOUNT_NOT_FOUND;
        } catch (Exception e) { return FORBIDDEN_REQUEST; }
        
        return LOGIN_SUCCESSFUL;
    }

    public String register (String[] data)
    {
        try
        {
            String clientName = data[1];
            int clientId = Integer.parseInt(data[2]);
            
            if (clientlist.get(clientId) == null)
            {
                ClientPrototype registedClient = new ClientPrototype(clientName, clientId);
                connectedClient = registedClient;
                clientlist.put(registedClient.id, registedClient);
                return REGISTRATION_SUCESSFUL;
            }

        } catch (Exception e ) { return FORBIDDEN_REQUEST; }
        return ACCOUNT_ALREADY_EXIST;
    }

    public void consult (String[] data)
    {   
        String clientName = data[1];
        String clientId = data[2];
    }

    public void sync (String[] data)
    {
        String clientName = data[1];
        String clientId = data[2];
    }

}