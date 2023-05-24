package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import server.protocols.RedirectProtocol;

public class Server
{
    public static String HOST_STRING = "localhost";
    public static int PORT = 8080;
    private static boolean isRunning;

    public static void main(String[] args) throws IOException
    {
        isRunning = true;
        ServerSocket socket = new ServerSocket(PORT);
        Executor pool = Executors.newCachedThreadPool();
        HashMap<Integer, ClientPrototype> clientdb = new HashMap<Integer, ClientPrototype>();
        
        loadClientDataBase(clientdb);

        System.out.println(String.format("Server is running! %s:%d", HOST_STRING, PORT));

        while (isRunning)
            try { pool.execute(new RedirectProtocol(socket.accept(), clientdb)); } catch (Exception e) { }

        socket.close();
    }

    public static void loadClientDataBase (HashMap<Integer, ClientPrototype> clientdb)
    {
        clientdb.put(2020, new ClientPrototype("cirilo", 2020, null));
        clientdb.put(2021, new ClientPrototype("ricilo", 2021, null));
        clientdb.put(2022, new ClientPrototype("sujiro_kimimami", 2022, null));
        clientdb.put(2023, new ClientPrototype("victor_sinistrao_da_silva", 2023, null));
        clientdb.put(2024, new ClientPrototype("galetos_e_galetos_rodizio_de_galetos", 2024, null));
    }
}