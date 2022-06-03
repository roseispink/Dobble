package com.example.dobble;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;


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
        @FXML
        void premiumAccess(){
            System.out.println("Siema");
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Dostęp premium");
            ButtonType type = new ButtonType("OK!", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Ta gra jest dostępna jedynie w wersji premium");
            dialog.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("dialog.css")).toExternalForm());
            dialog.getDialogPane().getStyleClass().add("myDialog");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }

        @FXML
        void goodBye(){
            stage.close();
            System.exit(0);
        }
    }

