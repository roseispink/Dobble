package com.example.dobble.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;

    private DataOutputStream bufferedWriter;
    private DataInputStream bufferedReader;
    private String clientUsername = "ble ble";

    public ClientHandler(Socket socket, Integer[] card) {
        try {
            this.socket = socket;
            this.bufferedReader = new DataInputStream((socket.getInputStream()));
            this.bufferedWriter= new DataOutputStream((socket.getOutputStream()));

            this.clientUsername = bufferedReader.readUTF();

            clientHandlers.add(this);
            System.out.println("SERVER: " + clientUsername + " has entered the chat!");
            System.out.println(clientHandlers.size());
            bufferedWriter.writeUTF(String.valueOf(clientHandlers.size()));
            bufferedWriter.flush();
            if(clientHandlers.size()>1){
                broadcastMessage("OPPONENT JOIN");
            }

            bufferedWriter.writeUTF("START "+card[0]+ " "+ card[1] + " "+ card[2]);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        System.out.println("run");
        String messageFromClient;

        while (socket.isConnected()) {
            try {

                messageFromClient = bufferedReader.readUTF();
                System.out.println(messageFromClient);
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                broadcastMessage("OPPONENT LEFT");
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.writeUTF(messageToSend);
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }



    public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {

        clientHandlers.remove(this);
        System.out.println("SERVER: " + clientUsername + " has left the chat!");
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
