package server;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server
{
    public static String  HOST_NAME = "localhost";
    public static int          PORT = 8080;
    private static boolean isRunning;

    public static void main(String[] args) throws Exception
    {
        isRunning = true;
        
        ServerSocket socket = new ServerSocket(PORT);
        Executor pool = Executors.newCachedThreadPool();
        
        HashMap<Integer, ClientPrototype> clients = new HashMap<Integer, ClientPrototype>();
        
        clients.put(123, new ClientPrototype("cirilo", 123));
        clients.put(321, new ClientPrototype("ricilo", 321));

        System.out.println(String.format("Server is running! %s:%d", HOST_NAME, PORT));

        while (isRunning)
        {
            try { pool.execute(new DataServerProtocol(socket.accept(), clients)); }

            catch (Exception e) { }
        }

        socket.close();
    }
}