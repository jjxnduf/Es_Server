package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        ServerSocket mioserver= new ServerSocket(3000);
        Socket mioSocket = mioserver.accept();
        System.out.println("qualcuno si e collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        PrintWriter out = new PrintWriter(mioSocket.getOutputStream(), true);

        String Sricevuta = in.readLine();
        String Smaiuscola = Sricevuta.toUpperCase();
        out.println(Smaiuscola);

        System.out.println(Sricevuta);
        mioSocket.close();
    }
}