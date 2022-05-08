package com.example.dobble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {

    @FXML
    private Button closeButton;

    @FXML
    private ImageView instruction;

    @FXML
    private Button mainGame;

    @FXML
    private Button miniGame1;

    @FXML
    private Button miniGame2;

    @FXML
    private Label title;

    @FXML
    private Button icon1;

    Stage stage = new Stage();
    Socket socketIn;
    Socket socketOut;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    @FXML
    void closeGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void showInstruction(MouseEvent event) {

    }

    @FXML
    void startMainGame(ActionEvent event) {
        try {
            new MainGame().start(stage);
            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void startMiniGame1(ActionEvent event) {

    }

    @FXML
    void startMiniGame2(ActionEvent event) {

    }


    @FXML
    void sendToServer(ActionEvent event) {
        if(outputStream!=null){
            try {
                outputStream.writeChars(icon1.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void connect(){
        try {
            socketIn = new Socket("localhost", 7); // do odczytu
            socketOut = new Socket("localhost", 7); //do zapisu
            inputStream = new ObjectInputStream(socketIn.getInputStream());
            outputStream = new ObjectOutputStream(socketOut.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void disconnect(){
        try {
            socketIn.close();
            socketOut.close();
            socketIn = null;
            socketOut = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
