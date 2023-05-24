package server.protocols;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import server.ClientPrototype;

public class RegisterProtocol implements Runnable, Protocol
{
    public HashMap<Integer, ClientPrototype> clientDB;
    public ClientPrototype loggedClient;
    public DataInputStream in;
    public DataOutputStream out;
    public String[] request;
    
    public RegisterProtocol ( DataInputStream in, DataOutputStream out, String[] request, ClientPrototype loggedClient, HashMap<Integer, ClientPrototype> clientDB )
    {
        this.clientDB = clientDB;
        this.in = in;
        this.out = out;
        this.request = request;
        this.loggedClient = loggedClient;
    }

    @Override
    public void run ()
    {
        try { out.writeUTF(register(request)); } catch (Exception e ) { }
    }

    public String register (String[] request)
    {
        if (loggedClient != null)
            return ALREADY_LOGGED;

        try 
        {
            String clientName = request[1];
            int clientId = Integer.parseInt(request[2]);
            
            if (clientDB.get(clientId) == null)
            {
                ClientPrototype regClient = new ClientPrototype(clientName, clientId, null);
                
                synchronized (this)
                { 
                    clientDB.put(regClient.id, regClient); 
                    System.out.println(regClient.name + "@" + regClient.id + " se registrou!");
                    return REGISTRATION_SUCESSFUL;
                }
            }
        } catch (Exception e ) { return FORBIDDEN_REQUEST; }
        
        return ACCOUNT_ALREADY_EXIST;
    }
}
