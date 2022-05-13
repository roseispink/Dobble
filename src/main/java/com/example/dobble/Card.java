package com.example.dobble;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Card {
    AnchorPane cardNumber;
    ArrayList<ImageView> iconList = new ArrayList<>(6);
    public void addIcon(ImageView v1, ImageView v2, ImageView v3, ImageView v4, ImageView v5, ImageView v6){
        iconList.add(v1);
        iconList.add(v2);
        iconList.add(v3);
        iconList.add(v4);
        iconList.add(v5);
        iconList.add(v6);
    }

    public void setCardNumber(AnchorPane cardNumber) {
        this.cardNumber = cardNumber;
    }

}
