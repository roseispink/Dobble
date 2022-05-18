package com.example.dobble;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.ArrayList;

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
    private Button methodCall;

    Card playerCard = new Card();
    Card stackCard =  new Card();
    private final int NUMBERS_OF_CARDS = 6;
    private int currentSizeStack = 0;
    ArrayList<ArrayList<String>> cardLayout = new ArrayList<>();
    //String path = "file:/Users/weronikakus/Desktop/Dobble1/target/classes/com/example/dobble/ikony/";
    String path = "file:/C:/Users/ala_s/IdeaProjects/Dobble_kol/target/classes/com/example/dobble/ikony/";
    String fileName = "Cards.txt";

    @FXML
    void calledMethod(MouseEvent event) {
        initialize();
        loadCardFromFile();
        drawStartCards();
    }

    void initialize(){
        playerCard.setCardNumber(card5);
        playerCard.addIcon(card5I1, card5I2, card5I3, card5I4, card5I5, card5I6);
        stackCard.setCardNumber(card1);
        stackCard.addIcon(card1I1, card1I2, card1I3, card1I4, card1I5, card1I6);
    }
    void loadCardFromFile(){
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

    void checkEquality(ImageView imageView){
        for (int i = 0; i < 6; i++) {
            if(imageView.getImage().getUrl().equals(playerCard.iconList.get(i).getImage().getUrl())){
                for(int j = 0; j < 6; j++){
                    Image img = new Image(stackCard.iconList.get(j).getImage().getUrl());
                    playerCard.iconList.get(j).setImage(img);

                    Image img1 = new Image(cardLayout.get(currentSizeStack).get(j));
                    stackCard.iconList.get(j).setImage(img1);
                }
                currentSizeStack++;
                break;
            }
        }
    }

    void drawStartCards(){
        int playerCardNum = (int) ((Math.random() * (NUMBERS_OF_CARDS-1)) + 0);
        System.out.println(playerCardNum);
        int mainCard = (int) ((Math.random() * (NUMBERS_OF_CARDS-1 )) + 0);
        while (mainCard == playerCardNum) mainCard = (int) ((Math.random() * (NUMBERS_OF_CARDS - 1)) + 0);
        System.out.println(mainCard);
        for(int j = 0; j < 6; j++){
            Image img = new Image(cardLayout.get(playerCardNum).get(j));
            playerCard.iconList.get(j).setImage(img);

            Image img1 = new Image(cardLayout.get(mainCard).get(j));
            stackCard.iconList.get(j).setImage(img1);
        }
        cardLayout.remove(playerCardNum);
        if(playerCardNum< mainCard) mainCard--;
        cardLayout.remove(mainCard);
    }

    @FXML
    void clickC1I1(MouseEvent event) {
       checkEquality(card1I1);
    }

    @FXML
    void clickCard1I2(MouseEvent event) {
       checkEquality(card1I2);
    }

    @FXML
    void clickCard1I3(MouseEvent event) {
        checkEquality(card1I3);
    }

    @FXML
    void clickCard1I4(MouseEvent event) {
        checkEquality(card1I4);
    }

    @FXML
    void clickCard1I5(MouseEvent event) {
        checkEquality(card1I5);
    }

    @FXML
    void clickCard1I6(MouseEvent event) {
        checkEquality(card1I6);
    }

    @FXML
    void clickCard5I1(MouseEvent event) {

    }

    @FXML
    void clickCard5I2(MouseEvent event) {

    }

    @FXML
    void clickCard5I4(MouseEvent event) {

    }

    @FXML
    void clickCard5I5(MouseEvent event) {

    }

    @FXML
    void clickCard5I6(MouseEvent event) {

    }

}
