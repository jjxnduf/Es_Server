package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws java.io.IOException {
        ServerSocket serverSocket = new ServerSocket(3001);
        System.out.println("Server avviato sulla porta 3001...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            MioThread t = new MioThread(clientSocket);
            t.start();
        }
    }
}
