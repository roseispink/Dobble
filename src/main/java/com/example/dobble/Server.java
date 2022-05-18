package com.example.dobble;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7);
        while(true){
            System.out.println("Czekam na klienta...");
            Socket sOut = serverSocket.accept();
            System.out.println("1");//polaczenie do zapisu
            /*Socket sIn = serverSocket.accept();
            System.out.println("2");//polaczenie do odczytu*/
            new ServerThread(sOut);
        }
    }
}

class ServerThread extends Thread{
    Socket sOut;
    //Socket sIn;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    ServerThread(Socket sOut) throws IOException{
        this.sOut =sOut;
        //this.sIn = sIn;
        outputStream = new DataOutputStream(this.sOut.getOutputStream());
        inputStream = new DataInputStream(this.sOut.getInputStream());
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Polaczono sie na porcie " + sOut.getPort());
        while (true){
            try {
                String read = inputStream.readUTF();
                System.out.println(read);
                outputStream.writeUTF(read);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
