package com.example.dobble;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CardController {

    @FXML
    private AnchorPane card1;

    @FXML
    private ImageView card1I1;

    @FXML
    private ImageView card1I2;

    @FXML
    private ImageView card1I3;

    @FXML
    private ImageView card1I4;

    @FXML
    private ImageView card1I5;

    @FXML
    private ImageView card1I6;

    @FXML
    private AnchorPane card5;

    @FXML
    private ImageView card5I1;

    @FXML
    private ImageView card5I2;

    @FXML
    private ImageView card5I3;

    @FXML
    private ImageView card5I4;

    @FXML
    private ImageView card5I5;

    @FXML
    private ImageView card5I6;

    @FXML
    private Label all;

    @FXML
    private Label yours;

    Card playerCard = new Card();
    boolean taken = false;
    Card stackCard =  new Card();

    String path = "";
    private int NUMBERS_OF_CARDS = 31;
    private int currentSizeStack = 0;
    ArrayList<ArrayList<String>> cardLayout = new ArrayList<>();
    String fileName = "Cards.txt";

    Connect client = new Connect();
    private boolean mainPlayer = true;



    public void checkDraw(){
        if(mainPlayer) drawStartCards();
    }

    public void getPath(){
        String [] split = card5I1.getImage().getUrl().split("/");
        for(int i = 0; i < split.length - 2; i++){
            path += split[i] + "/";
        }
        System.out.println(path);
    }

    public void connect(){
        client.connect();
        Timer timer =  new Timer();
        TimerTask getMess = new TimerTask() {
            @Override
            public void run() {
                String mess = client.getFromServer();
                if(mess.equals("1")) {
                    System.out.println("Main");
                    mainPlayer = true;
                }
                else if(mess.equals("2")) {
                    System.out.println("Not main");
                    mainPlayer = false;
                }
                if(mess.equals("TAKEN")) taken = true;
                if(mess.startsWith("STACKCARD") && !mainPlayer){
                    System.out.println("jeden tylko");
                    int playerCardNum = (int) ((Math.random() * (NUMBERS_OF_CARDS-1)) + 0);
                    String [] tab = mess.split(" ");
                    int mainCard = Integer.getInteger(tab[0]);
                    while (mainCard == playerCardNum) playerCardNum = (int) ((Math.random() * (NUMBERS_OF_CARDS - 1)) + 0);
                    for(int j = 0; j < 6; j++){
                        Image img = new Image(cardLayout.get(playerCardNum).get(j));
                        playerCard.iconList.get(j).setImage(img);

                        Image img1 = new Image(cardLayout.get(mainCard).get(j));
                        stackCard.iconList.get(j).setImage(img1);
                    }
                    cardLayout.remove(playerCardNum);
                    if(playerCardNum< mainCard) mainCard--;
                    cardLayout.remove(mainCard);
                    mainPlayer = false;
                }
            }
        };
        timer.schedule(getMess, 300, 1000);
    }


    public void initialize(){
        playerCard.setCardNumber(card5);
        playerCard.addIcon(card5I1, card5I2, card5I3, card5I4, card5I5, card5I6);

        stackCard.setCardNumber(card1);
        stackCard.addIcon(card1I1, card1I2, card1I3, card1I4, card1I5, card1I6);

        all.setText(String.valueOf(NUMBERS_OF_CARDS-2));
        yours.setText(String.valueOf(currentSizeStack));
    }

    public void loadCardFromFile(){
        try {
            BufferedReader fp = new BufferedReader(new FileReader(fileName));
            short j = 0;
            for (int k = 0; k < NUMBERS_OF_CARDS-1; k++) {
                cardLayout.add(new ArrayList<>());
                for (int i = 0; i < 6; i++) {
                    String line = path;
                    line += fp.readLine();
                    cardLayout.get(j).add(line);
                }
                String space = fp.readLine();
                j++;
            }
            fp.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void checkEquality(ImageView imageView){
        for (int i = 0; i < 6; i++) {
            if(imageView.getImage().getUrl().equals(playerCard.iconList.get(i).getImage().getUrl())){
                client.sendToServer("TAKEN");
                for(int j = 0; j < 6; j++){
                    Image img = new Image(stackCard.iconList.get(j).getImage().getUrl());
                    playerCard.iconList.get(j).setImage(img);

                    Image img1 = new Image(cardLayout.get(currentSizeStack).get(j));
                    stackCard.iconList.get(j).setImage(img1);
                }
                taken = false;
                currentSizeStack++;
                NUMBERS_OF_CARDS--;
                all.setText(String.valueOf(NUMBERS_OF_CARDS-2));
                yours.setText(String.valueOf(currentSizeStack));
                yours.setTextFill(Color.AQUAMARINE);
                break;
            }
        }
    }

    public void drawStartCards(){
        if(mainPlayer) {
            int playerCardNum = (int) ((Math.random() * (NUMBERS_OF_CARDS - 1)) + 0);
            System.out.println(playerCardNum);
            int mainCard = (int) ((Math.random() * (NUMBERS_OF_CARDS - 1)) + 0);
            while (mainCard == playerCardNum) mainCard = (int) ((Math.random() * (NUMBERS_OF_CARDS - 1)) + 0);
            client.sendToServer("STACKCARD " + mainCard);
            System.out.println(mainCard);
            for (int j = 0; j < 6; j++) {
                Image img = new Image(cardLayout.get(playerCardNum).get(j));
                playerCard.iconList.get(j).setImage(img);

                Image img1 = new Image(cardLayout.get(mainCard).get(j));
                stackCard.iconList.get(j).setImage(img1);
            }
            cardLayout.remove(playerCardNum);
            if (playerCardNum < mainCard) mainCard--;
            cardLayout.remove(mainCard);
        }
    }

    @FXML
    void clickC1I1(MouseEvent event) {
       if(!taken) checkEquality(card1I1);
    }

    @FXML
    void clickCard1I2(MouseEvent event) {
       if(!taken) checkEquality(card1I2);
    }

    @FXML
    void clickCard1I3(MouseEvent event) {
        if(!taken) checkEquality(card1I3);
    }

    @FXML
    void clickCard1I4(MouseEvent event) {
        if(!taken) checkEquality(card1I4);
    }

    @FXML
    void clickCard1I5(MouseEvent event) {
        if(!taken) checkEquality(card1I5);
    }

    @FXML
    void clickCard1I6(MouseEvent event) {
        if(!taken) checkEquality(card1I6);
    }


}
