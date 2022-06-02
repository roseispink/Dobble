package com.example.dobble;

import java.net.Socket;

import java.io.*;

public class Connect {
    Socket socketIn;
    //private BufferedReader bufferedReader;
    //private BufferedWriter bufferedWriter;

    private DataInputStream bufferedReader;
    private DataOutputStream bufferedWriter;

    void connect() {
        try {
            System.out.println("łączenie");
            socketIn = new Socket("localhost", 1234); // do odczytu
            bufferedReader = new DataInputStream((socketIn.getInputStream()));
            bufferedWriter = new DataOutputStream((socketIn.getOutputStream()));
            bufferedWriter.writeUTF(String.valueOf(socketIn.getLocalPort()));
            bufferedWriter.flush();
            System.out.println(socketIn.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendToServer(String string) {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.writeUTF(string);
                bufferedWriter.flush();
                System.out.println("Send to Server");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("null");
        }
    }

    String getFromServer() {
         String msgFromGroupChat;
        // While there is still a connection with the server, continue to listen for messages on a separate thread.
                // Get the messages sent from other users and print it to the console.
        try {
            msgFromGroupChat = bufferedReader.readUTF();
            System.out.println("received from server");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return msgFromGroupChat;
    }

    public void disconnect(){
        try {
            bufferedWriter.close();
            bufferedReader.close();
            socketIn.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}