package com.example.dobble.Network;

import com.example.dobble.Controllers.CardController;

import java.net.*;
import java.io.*;

public class Server{

    private final Integer []card = new Integer[3];
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {

            while (!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket, card);
                Thread thread = new Thread(clientHandler);

                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    private void drawCards(){
        int num = new CardController().getNUMBERS_OF_CARDS();
        int player1 = (int) ((Math.random() * (num - 1)) + 0);
        int player2 = (int) ((Math.random() * (num - 1)) + 0);
        while (player2 == player1) player2 = (int) ((Math.random() * (num - 1)) + 0);
        int mainCard = (int) ((Math.random() * (num - 1)) + 0);
        while (mainCard == player1 || mainCard == player2) mainCard = (int) ((Math.random() * (num - 1)) + 0);
        card[0] = mainCard;
        card[1] = player1;
        card[2] = player2;
    }


    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.drawCards();
        server.startServer();
    }

}

