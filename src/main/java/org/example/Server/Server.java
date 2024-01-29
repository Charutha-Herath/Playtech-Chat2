package org.example.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private static Server server;
    private final ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(5000);
        System.out.println("Server Started");
    }
    public static Server getServerSocket() throws IOException {
        return server == null ? server  = new Server() : server;

    }

    @Override
    public void run() {
        while (!serverSocket.isClosed()){
            System.out.println("Listening....");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accepted");
                ClientHandler clientHandler =new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
