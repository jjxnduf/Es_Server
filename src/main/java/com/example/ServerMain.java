package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("Inizio server");

        ServerSocket serverSocket = new ServerSocket(3001);
        System.out.println("Server in ascolto sulla porta 3001");

        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.println("Client connesso.");

        String received;
        while ((received = in.readLine()) != null) {
            System.out.println("Ricevuto: " + received);

            
            String[] parts = received.split(" ");
            if (parts.length != 3) {
                out.println("Errore: formato non valido");
            }

            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[1]);
            int op = Integer.parseInt(parts[2]);

            if (op < 1 || op > 4) {
                System.out.println("Il client ha chiesto di chiudere la connessione.");
                break;
            }

            int risultato = 0;

            switch (op) {
                case 1:
                    risultato = num1 + num2;
                    break;
                case 2:
                    risultato = num1 - num2;
                    break;
                case 3:
                    if (num2 == 0) {
                        out.println("Errore: divisione per zero");
                    }
                    risultato = num1 / num2;
                    break;
                case 4:
                    risultato = num1 * num2;
                    break;
                default:
                    break;
            }
            
            out.println("Risultato: " + risultato);
        }

        System.out.println("Connessione chiusa.");
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
