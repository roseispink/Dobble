package com.example.dobble;

import com.example.dobble.Controllers.HelloController;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage){
        new HelloController().start();
    }

    public static void main(String[] args) {
        launch();
    }
}