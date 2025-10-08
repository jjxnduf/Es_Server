package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Inizio server");

        try (ServerSocket serverSocket = new ServerSocket(3001)) {
            System.out.println("Server in ascolto in porta 3001");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connesso.");

                String received;

                while ((received = in.readLine()) != null) {
                    if (received.equals("!")) {
                        System.out.println("Il client vuole chiudere la connessione.");
                        break;
                    }

                    System.out.println("Ricevuto: " + received);
                    String uppercased = received.toUpperCase();
                    out.println(uppercased);
                }

                System.out.println("Connessine terminata dal client");
            }
        }
    }
}
