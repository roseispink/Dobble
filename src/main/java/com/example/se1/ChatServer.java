package com.example.se1;

import javafx.animation.AnimationTimer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.Scanner;


public class ChatServer extends Stage {
    Label currentPlayer = new Label("Player1:");
    Label currentTimer = new Label();
    Label messageLabel = new Label("...");
    Button startStop = new Button();
    private static Socket socket;
    private Scanner in;
    private PrintWriter out;

    public ChatServer(String serverAddress) throws Exception {
        VBox root = new VBox(10, currentPlayer, currentTimer, startStop, messageLabel);
        root.setAlignment(Pos.CENTER);
        setScene(new Scene(root, 320, 200));
        show();
        socket = new Socket(serverAddress, 150);
        in = new Scanner(socket.getInputStream());

        startTimer();
    }

    public void startTimer() {
        DoubleProperty time = new SimpleDoubleProperty();
        currentTimer.textProperty().bind(time.asString("%.3f seconds"));
        BooleanProperty running = new SimpleBooleanProperty();

        AnimationTimer timer = new AnimationTimer() {

            private long startTime;
            double number;

            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                running.set(true);
                super.start();
            }

            @Override
            public void stop() {
                running.set(false);
                number = time.doubleValue();
                try{
                sendMessage(number);}
                catch(IOException e){
                    e.printStackTrace();
                }
                super.stop();
            }

            @Override
            public void handle(long timestamp) {
                long now = System.currentTimeMillis();
                time.set((now - startTime) / 1000.0);
            }
        };

        startStop.textProperty().bind(
                Bindings.when(running)
                        .then("Stop")
                        .otherwise("Start"));

        startStop.setOnAction(e -> {
            if (running.get()) {
                timer.stop();
            } else {
                timer.start();
            }
        });

    }

    void sendMessage(double number) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(number);
        messageLabel.textProperty().set("Wysłano "+ number);
    }
}

