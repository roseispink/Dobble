package com.example.se1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(58901)) {
            System.out.println("Tic Tac Toe Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Handler handler = new Handler();
                pool.execute(handler.new Player(1, listener.accept(), true));
                pool.execute(handler.new Player(2, listener.accept(), false));
            }
        }
    }
}

class Handler {

    // Board cells numbered 0-8, top to bottom, left to right; null if empty
    private Player[] players = new Player[2];

    Player currentPlayer;


    class Player implements Runnable {

        int number;
        Socket socket;
        boolean currentPlayer;
        PrintWriter output;
        Scanner input;
        Double oldTime = 0.0;

        Double getOldTime() {
            return oldTime;
        }

        Player(int num, Socket sc, boolean currentPlayer) throws IOException {
            socket = sc;
            number = num;
            this.currentPlayer = currentPlayer;
            System.out.println("Połączono: " + socket + " głowny gracz: " + currentPlayer);

            output = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            System.out.println("running");
            try {
                input = new Scanner(socket.getInputStream());
                command();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void command() {
            while (input.hasNextLine()) {
                var stringTime = input.nextLine();
                oldTime += Double.parseDouble(stringTime);
                System.out.println(oldTime);

            }
        }
    }
}

