package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;

public class MioThread extends Thread {
    private Socket mySocket;

    public MioThread(Socket socket) {
        this.mySocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            PrintWriter out = new PrintWriter(mySocket.getOutputStream(), true)
        ) {
            System.out.println("Client connesso: " + mySocket.getInetAddress());

            
            out.println("Versione 2");

            String line1, line2, line3;

            while (true) {
                line1 = in.readLine();
                line2 = in.readLine();
                line3 = in.readLine();

                if (line1 == null || line2 == null || line3 == null) {
                    System.out.println("Il client ha chiuso la connessione.");
                    break;
                }

                int num1, num2, op;

                try {
                    num1 = Integer.parseInt(line1.trim());
                    num2 = Integer.parseInt(line2.trim());
                    op = Integer.parseInt(line3.trim());
                } catch (NumberFormatException e) {
                    out.println("Errore: input non numerico.");
                    continue;
                }

                if (op < 1 || op > 4) {
                    out.println("Operazione non valida. Connessione chiusa.");
                    break;
                }

                int risultato = 0;
                boolean operazioneValida = true;

                switch (op) {
                    case 1:
                        risultato = num1 + num2;
                        break;
                    case 2:
                        risultato = num1 - num2;
                        break;
                    case 3:
                        if (num2 == 0) {
                            out.println("Errore: divisione per zero.");
                            operazioneValida = false;
                        } else {
                            risultato = num1 / num2;
                        }
                        break;
                    case 4:
                        risultato = num1 * num2;
                        break;
                }

                if (operazioneValida) {
                    out.println("Risultato: " + risultato);
                }
            }

            System.out.println("Client disconnesso: " + mySocket.getInetAddress());

        } catch (IOException e) {
            System.err.println("Errore nella comunicazione con il client.");
            e.printStackTrace();
        } finally {
            try {
                mySocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
