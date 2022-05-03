package com.example.se1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       stage =  new ChatServer("192.168.1.202");
    }

    public static void main(String[] args) {
        launch();
    }
}