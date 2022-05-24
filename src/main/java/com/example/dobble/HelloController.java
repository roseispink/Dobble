package com.example.dobble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("main-game-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
                stage.setScene(scene);
                stage.show();
                ((CardController)fxmlLoader.getController()).connect();
                ((CardController)fxmlLoader.getController()).getPath();
                ((CardController)fxmlLoader.getController()).initialize();
                ((CardController)fxmlLoader.getController()).loadCardFromFile();
                ((CardController)fxmlLoader.getController()).drawStartCards();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

