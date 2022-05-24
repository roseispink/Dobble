package com.example.dobble;

import javafx.fxml.FXMLLoader;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    public List<Socket> clientList = new ArrayList<>();
    ServerSocket serverSocket;

    Server(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        System.out.println("Czekam na graczy...");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                this.clientList.add(client);
                ServerThread serverThread = new ServerThread(client, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String [] args) throws IOException {
        Server serv = new Server(7);
        serv.start();
    }
}

class ServerThread extends Thread{
    Socket player1;
    Server server;
    int pip = 1;

    //Socket sIn;
    private DataInputStream inStreamP1;
    private DataOutputStream outStreamP1;


    ServerThread(Socket s1, Server server) throws IOException{
        this.player1 =s1;
        this.server = server;
        outStreamP1 = new DataOutputStream(this.player1.getOutputStream());
        inStreamP1 = new DataInputStream(this.player1.getInputStream());
        outStreamP1.writeUTF(String.valueOf(pip));
        pip++;
        outStreamP1.flush();
        this.start();
    }

    @Override
    public void run(){
        while (true){
            try {
                String mess =  inStreamP1.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
