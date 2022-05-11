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
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    ServerThread(Socket sOut) throws IOException{
        this.sOut =sOut;
        //this.sIn = sIn;
        System.out.println("knstrktor");
        outputStream = new ObjectOutputStream(this.sOut.getOutputStream());
        System.out.println(outputStream);
        inputStream = new ObjectInputStream(this.sOut.getInputStream());
        System.out.println(inputStream);
        this.start();
    }

    @Override
    public void run() {
        System.out.println("Polaczono sie na porcie " + sOut.getPort());
        while (true){
            try {
                System.out.println("otworzono");
                String read = inputStream.readUTF();
                System.out.println("zczytano");
                System.out.println(read);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
