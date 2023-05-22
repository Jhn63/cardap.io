package server;

import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server
{
    public static String  HOST_NAME = "localhost";
    public static int          PORT = 8080;
    private static boolean isRunning;

    public static void main(String[] args) throws Exception
    {
        ServerSocket socket = new ServerSocket(PORT);
        Executor pool = Executors.newCachedThreadPool();
        isRunning = true;

        System.out.println(String.format("Server is running! %s:%d", HOST_NAME, PORT));

        while (isRunning)
        {
            try { pool.execute(new DataServerProtocol(socket.accept())); } 
            catch (Exception e) { }
        }

        socket.close();
    }
}