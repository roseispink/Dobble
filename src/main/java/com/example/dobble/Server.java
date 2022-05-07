package com.example.dobble;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7);
        while(true){
            System.out.println("Czekam na klienta...");
            Socket sOut = serverSocket.accept(); //polaczenie do zapisu
            Socket sIn = serverSocket.accept(); //polaczenie do odczytu
            new ServerThread(sOut, sIn);
        }
    }
}

class ServerThread extends Thread{
    Socket sOut;
    Socket sIn;

    ServerThread(Socket sOut, Socket sIn){
        this.sOut =sOut;
        this.sIn = sIn;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Poloczono sie na porcie "+ sIn.getPort()+ " i na porcie" + sOut.getPort());
    }
}
