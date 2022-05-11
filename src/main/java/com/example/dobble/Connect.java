package com.example.dobble;

import javafx.event.ActionEvent;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Connect {
    Socket socketIn;
    private ObjectInputStream inputStream;
    private PrintWriter outputStream;

    void connect(){
        try {
            System.out.println("łączenie");
            socketIn = new Socket("localhost", 7); // do odczytu
            System.out.println("2");
            outputStream = new PrintWriter(socketIn.getOutputStream());
            inputStream = new ObjectInputStream(socketIn.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void sendToServer(String string) {
        System.out.println("sendtoserver");
        if (outputStream != null) {
            try {
                outputStream.print(string);
                System.out.println("wyslano");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("null");
        }
    }
}
