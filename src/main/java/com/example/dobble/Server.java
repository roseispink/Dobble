package com.example.dobble;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server{

    private final Integer []card = new Integer[3];
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            // Listen for connections (clients to connect) on port 1234.

            while (!serverSocket.isClosed()) {
                // Will be closed in the Client Handler.
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket, card);
                Thread thread = new Thread(clientHandler);
                // The start method begins the execution of a thread.
                // When you call start() the run method is called.
                // The operating system schedules the threads.
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



    // Close the server socket gracefully.
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run the program.
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.drawCards();
        server.startServer();
    }

}

class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    // Id that will increment with each new client.


    // Socket for a connection, buffer reader and writer for receiving and sending data respectively.
    private Socket socket;
    //private BufferedReader bufferedReader;
    //private BufferedWriter bufferedWriter;

    private DataOutputStream bufferedWriter;
    private DataInputStream bufferedReader;
    private String clientUsername = "ble ble";

    // Creating the client handler from the socket the server passes.
    public ClientHandler(Socket socket, Integer[] card) {
        try {
            this.socket = socket;
            this.bufferedReader = new DataInputStream((socket.getInputStream()));
            this.bufferedWriter= new DataOutputStream((socket.getOutputStream()));
            // When a client connects their username is sent.
            this.clientUsername = bufferedReader.readUTF();
            // Add the new client handler to the array so they can receive messages from others.
            clientHandlers.add(this);
            System.out.println("SERVER: " + clientUsername + " has entered the chat!");
            System.out.println(clientHandlers.size());
            bufferedWriter.writeUTF(String.valueOf(clientHandlers.size()));
            bufferedWriter.flush();
            if(clientHandlers.size()>1){
                clientHandlers.get(0).bufferedWriter.writeUTF("OPPONENT JOIN");
                bufferedWriter.flush();
            }


            //initialize start cards
            bufferedWriter.writeUTF("START "+card[0]+ " "+ card[1] + " "+ card[2]);
        } catch (IOException e) {
            // Close everything more gracefully.
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // Everything in this method is run on a separate thread. We want to listen for messages
    // on a separate thread because listening (bufferedReader.readLine()) is a blocking operation.
    // A blocking operation means the caller waits for the callee to finish its operation.
    @Override
    public void run() {
        System.out.println("run");
        String messageFromClient;
        // Continue to listen for messages while a connection with the client is still established.
        while (socket.isConnected()) {
            try {
                // Read what the client sent and then send it to every other client.
                messageFromClient = bufferedReader.readUTF();
                System.out.println(messageFromClient);
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                // Close everything gracefully.
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    // Send a message through each client handler thread so that everyone gets the message.
    // Basically each client handler is a connection to a client. So for any message that
    // is received, loop through each connection and send it down it.
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                // You don't want to broadcast the message to the user who sent it.
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.writeUTF(messageToSend);
                    //clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                // Gracefully close everything.
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    // If the client disconnects for any reason remove them from the list so a message isn't sent down a broken connection.
    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left the chat!");
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {
        // Note you only need to close the outer wrapper as the underlying streams are closed when you close the wrapper.
        // Note you want to close the outermost wrapper so that everything gets flushed.
        // Note that closing a socket will also close the socket's InputStream and OutputStream.
        // Closing the input stream closes the socket. You need to use shutdownInput() on socket to just close the input stream.
        // Closing the socket will also close the socket's input stream and output stream.
        // Close the socket after closing the streams.

        // The client disconnected or an error occurred so remove them from the list so no message is broadcasted.
        removeClientHandler();
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
