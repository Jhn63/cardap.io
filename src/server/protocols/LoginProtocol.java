package server.protocols;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import server.ClientPrototype;

public class LoginProtocol implements Runnable, Protocol
{
    public HashMap<Integer, ClientPrototype> clientDB;
    public ClientPrototype loggedClient;
    public DataInputStream in;
    public DataOutputStream out;
    public String[] request;

    public LoginProtocol ( DataInputStream in, DataOutputStream out, String[] request, ClientPrototype loggedClient, HashMap<Integer, ClientPrototype> clientDB )
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
        try { out.writeUTF(login(request)); } catch (Exception e ) {}
    }
    
    public String login (String[] request)
    {
        if (this.loggedClient != null) 
            return ALREADY_LOGGED;
        
        try {
            
            int clientId = Integer.parseInt(request[2]);

            this.loggedClient = clientDB.get(clientId);

            if (this.loggedClient == null) 
                return ACCOUNT_NOT_FOUND;
            
        } catch (Exception e) { return FORBIDDEN_REQUEST; }
        
        System.out.println(loggedClient.name + "@" + loggedClient.id + " fez login!");
        
        return LOGIN_SUCCESSFUL;
    }
}
