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
import java.io.PrintWriter;
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

    //Socket socketOut;


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

    }

    void connect(){

    }

    void disconnect(){

    }

}
