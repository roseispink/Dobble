package com.example.dobble;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CardController {

    @FXML
    private ImageView Card2I5;

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

    Card kCard1 = new Card();
    @FXML
    private AnchorPane card2;

    @FXML
    private ImageView card2I1;

    @FXML
    private ImageView card2I2;

    @FXML
    private ImageView card2I3;

    @FXML
    private ImageView card2I4;

    @FXML
    private ImageView card2I6;
    Card kCard2 = new Card();

    CardController(){
        kCard1.setCardNumber(card1);
        kCard1.addIcon(card1I1,card1I2,card1I3,card1I4,card1I5,card1I6);
        kCard2.setCardNumber(card2);
        kCard2.addIcon(card2I1,card2I2,card2I3,card2I4, Card2I5, card2I6);

    }

    @FXML
    void clickC1I1(MouseEvent event) {
        for(int i = 0; i < 6; i++){
            if(card1I1.getImage().getUrl().equals(kCard2.iconList.get(i).getImage().getUrl())) card1.relocate(772, 209);
        }

    }

    @FXML
    void clickCard1I2(MouseEvent event) {

    }

    @FXML
    void clickCard1I3(MouseEvent event) {

    }

    @FXML
    void clickCard1I4(MouseEvent event) {

    }

    @FXML
    void clickCard1I5(MouseEvent event) {

    }

    @FXML
    void clickCard1I6(MouseEvent event) {

    }

    @FXML
    void clickCard2I1(MouseEvent event) {

    }

    @FXML
    void clickCard2I2(MouseEvent event) {

    }

    @FXML
    void clickCard2I3(MouseEvent event) {

    }

    @FXML
    void clickCard2I4(MouseEvent event) {

    }

    @FXML
    void clickCard2I5(MouseEvent event) {

    }

    @FXML
    void clickCard2I6(MouseEvent event) {

    }

}