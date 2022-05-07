package com.example.dobble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    Stage stage = new Stage();
    Socket socketIn;
    Socket socketOut;

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

    void connect(){
        try {
            socketIn = new Socket("localhost", 7); // do odczytu
            socketOut = new Socket("localhost", 7); //do zapisu
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
