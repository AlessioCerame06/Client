package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Il client è partito");
        Scanner scanner = new Scanner(System.in);
        Socket mySocket = new Socket("localhost", 3000);
        System.out.println("Il client si è collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        DataOutputStream out = new DataOutputStream(mySocket.getOutputStream());

        do {
            System.out.println("Scrivi una stringa");
            System.out.println("Scrivere 'exit' se si vuole chiudere");
            String str = scanner.nextLine();
            if (str.equals("exit")){
                System.out.println("Chiudo");
                out.writeBytes("!" + "\n");
                mySocket.close();
                break;
            }
            out.writeBytes(str + "\n");
            String stringaRicevuta = in.readLine();

            System.out.println("La stringa ricevuta: " + stringaRicevuta);
        } while (true);
    }
}