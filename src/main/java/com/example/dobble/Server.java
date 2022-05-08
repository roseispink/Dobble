package com.example.dobble;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    ServerThread(Socket sOut, Socket sIn) throws IOException{
        this.sOut =sOut;
        this.sIn = sIn;
        inputStream = new ObjectInputStream(sIn.getInputStream());
        outputStream = new ObjectOutputStream(sOut.getOutputStream());
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Polaczono sie na porcie "+ sIn.getPort()+ " i na porcie" + sOut.getPort());
        while (true){
            try {
                String read = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
