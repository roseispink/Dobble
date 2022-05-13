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
        private Button mainGameB;
        Stage stage = new Stage();


        @FXML
        void startMainGame(MouseEvent event) {
            try {
                new MainGame().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

