package com.example.dobble;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class HelloController {

    Stage stageI = new Stage();
    Stage stageM = new Stage();
    Stage stage = new Stage();
    Scene sc = null;

    @FXML
    private Button mainGameB;


        public void start(){
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            sc = new Scene(fxmlLoader.load(), 870, 570);
                stage.setScene(sc);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @FXML
        void showInstruction() throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("instruction-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 415);
            stageI.setScene(scene);
            stageI.show();
        }


        @FXML
        void startMainGame() {
            Stage stage = (Stage) mainGameB.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("main-game-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
                stageM.setScene(scene);
                stageM.show();
                ((CardController)fxmlLoader.getController()).getPath();
                ((CardController)fxmlLoader.getController()).initialize();
                ((CardController)fxmlLoader.getController()).loadCardFromFile();
                ((CardController)fxmlLoader.getController()).connect();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

