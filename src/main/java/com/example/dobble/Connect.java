package com.example.dobble;

import javafx.event.ActionEvent;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import java.net.*;
import java.io.*;

public class Connect {
    Socket socketIn;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    void connect(){
        try {
            System.out.println("łączenie");
            socketIn = new Socket("localhost", 7); // do odczytu
            System.out.println("2");
            outputStream = new DataOutputStream(socketIn.getOutputStream());
            inputStream = new DataInputStream(socketIn.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void sendToServer(String string) {
        System.out.println("sendtoserver");
        if (outputStream != null) {
            try {
                outputStream.writeUTF(string);
                System.out.println("Send to Server");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("null");
        }
    }

    void getFromServer(){
        if(inputStream!=null){
            try{
                inputStream.readUTF();
                System.out.println("Received from Server");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else System.out.println("null");
    }
}