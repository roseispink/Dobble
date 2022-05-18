package com.example.dobble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGame.class.getResource("main-game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 780);
        stage.setScene(scene);
        stage.show();
        //Connect client = new Connect();
        //client.connect();
        //client.sendToServer("Hello");
    }
}
