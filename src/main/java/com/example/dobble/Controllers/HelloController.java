package com.example.dobble.Controllers;

import com.example.dobble.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
            DialogPane dialog;
            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dostęp premium");
            alert.setHeaderText(null);
            alert.setContentText("Ta gra jest dostępna jedynie w wersji premium!");
            dialog = alert.getDialogPane();
            dialog.getStylesheets().add(Objects.requireNonNull(getClass().getResource("dialog.css")).toString());
            dialog.getStyleClass().add("myDialog");
            ImageView image = new ImageView(Objects.requireNonNull(this.getClass().getResource("crown.png")).toString());
            alert.setGraphic(image);
            alert.showAndWait();
        }

        @FXML
        void goodBye(){
            stage.close();
            System.exit(0);
        }
    }

