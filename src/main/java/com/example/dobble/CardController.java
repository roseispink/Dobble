package com.example.dobble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;


public class CardController {

    @FXML
    private Circle circle1;
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


    @FXML
    private Rectangle playerRec;

    @FXML
    private Rectangle stackRec;


    Card playerCard = new Card();
    boolean taken = false;
    Card stackCard =  new Card();

    String path = "";
    private final int NUMBERS_OF_CARDS = 5;
    private int currentSizeStack = 0;
    private int points = NUMBERS_OF_CARDS-3;
    ArrayList<ArrayList<String>> cardLayout = new ArrayList<>();
    String fileName = "Cards.txt";

    Connect client = new Connect();
    private boolean mainPlayer = true;



    public int getNUMBERS_OF_CARDS(){ return NUMBERS_OF_CARDS;}

    public void getPath(){
        String [] split = card5I1.getImage().getUrl().split("/");
        for(int i = 0; i < split.length - 2; i++){
            path += split[i] + "/";
        }
    }

    public void connect(){
        client.connect();

        new Thread(() -> {
            while (true){
                String mess = client.getFromServer();
                if(mess.equals("END")) break;
                if(mess.equals("1"))  mainPlayer = true;
                else if(mess.equals("2")) mainPlayer = false;
                if(mess.equals("TAKEN")) {
                    taken = true;
                    for(int j = 0; j < 6; j++){
                        Image img1 = new Image(cardLayout.get(currentSizeStack).get(j));
                        stackCard.iconList.get(j).setImage(img1);
                    }
                    currentSizeStack++;
                    all.setText(String.valueOf(points-2-currentSizeStack));
                    taken = false;
                }
                if(mess.startsWith("START")){
                    String [] tab = mess.split(" ");
                    int mainCard = Integer.parseInt(tab[1]);
                    int mainPlayerCard, opponentCard;
                    if(mainPlayer) {
                        mainPlayerCard = Integer.parseInt(tab[2]);
                        opponentCard = Integer.parseInt(tab[3]);
                    }
                    else {
                        mainPlayerCard = Integer.parseInt(tab[3]);
                        opponentCard = Integer.parseInt(tab[2]);
                    }
                    System.out.println(cardLayout.size());
                    System.out.println(cardLayout.get(mainCard
                    ));
                    System.out.println(cardLayout.get(mainPlayerCard
                    ));
                    for(int j = 0; j < 6; j++){
                        Image img1 = new Image(cardLayout.get(mainCard).get(j));
                        stackCard.iconList.get(j).setImage(img1);

                        Image img = new Image(cardLayout.get(mainPlayerCard).get(j));
                        playerCard.iconList.get(j).setImage(img);
                    }
                    cardLayout.remove(mainCard);
                    if(mainPlayerCard < opponentCard) opponentCard--;
                    if(mainCard< mainPlayerCard) mainPlayerCard--;
                    cardLayout.remove(mainPlayerCard);
                    if(mainCard < opponentCard) opponentCard--;
                    cardLayout.remove(opponentCard);

                    System.out.println("cardLayout = " + cardLayout.size());
                }
            }
        }).start();
    }


    public void initialize(){
        playerCard.setCardNumber(card5);
        playerCard.addIcon(card5I1, card5I2, card5I3, card5I4, card5I5, card5I6);

        stackCard.setCardNumber(card1);
        stackCard.addIcon(card1I1, card1I2, card1I3, card1I4, card1I5, card1I6);

        all.setText(String.valueOf(points));
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
                showInfo(Color.GREEN);
                client.sendToServer("TAKEN");
                points--;

                System.out.println(points);

                if(points==0){
                    System.out.println("koniec");
                    circle1.setVisible(false);
                    all.setText(String.valueOf(points));
                    yours.setText(String.valueOf(++currentSizeStack));
                    for(int j = 0; j < 6; j++){
                        stackCard.iconList.get(j).setVisible(false);
                        Image img = new Image(cardLayout.get(currentSizeStack-2).get(j));
                        playerCard.iconList.get(j).setImage(img);
                    }
                    return;
                }
                for(int j = 0; j < 6; j++){
                    Image img = new Image(stackCard.iconList.get(j).getImage().getUrl());
                    playerCard.iconList.get(j).setImage(img);

                    Image img1 = new Image(cardLayout.get(currentSizeStack).get(j));
                    stackCard.iconList.get(j).setImage(img1);
                }

                taken = false;
                currentSizeStack++;

                all.setText(String.valueOf(points));
                yours.setText(String.valueOf(currentSizeStack));

                return;
            }
        }
        showInfo(Color.RED);
    }

    private void showInfo(Color color){
        new Thread(()->{
            stackRec.setFill(color);
            try {
                Thread.sleep(Duration.ofSeconds(1).toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stackRec.setFill(Color.valueOf("#616cbc"));
        }).start();
    }


    @FXML
    void clickC1I1() {
       if(!taken) checkEquality(card1I1);
    }

    @FXML
    void clickCard1I2() {
       if(!taken) checkEquality(card1I2);
    }

    @FXML
    void clickCard1I3() {
        if(!taken) checkEquality(card1I3);
    }

    @FXML
    void clickCard1I4() {
        if(!taken) checkEquality(card1I4);
    }

    @FXML
    void clickCard1I5() {
        if(!taken) checkEquality(card1I5);
    }

    @FXML
    void clickCard1I6() {
        if(!taken) checkEquality(card1I6);
    }

    @FXML
    void exitAll() {
        System.exit(0);
    }

    @FXML
    void returnToMenu(ActionEvent event) {
        new HelloController().start();
    }


}
