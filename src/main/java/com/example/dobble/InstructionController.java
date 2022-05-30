package com.example.dobble;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class InstructionController {

    @FXML
    private RadioButton mainIst;
    @FXML
    private RadioButton miniIst1;
    @FXML
    private RadioButton miniIst2;
    @FXML
    private TextArea textArea;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private boolean isSetGroup = false;

    public void setToggleGroup() {
        mainIst.setToggleGroup(toggleGroup);
        miniIst1.setToggleGroup(toggleGroup);
        miniIst2.setToggleGroup(toggleGroup);
        isSetGroup = true;
    }

    @FXML
    void setMainIst() {
        if(!isSetGroup) setToggleGroup();
        textArea.setText("Należy jak najszybciej odnaleźć symbol znajdujący się jednocześnie na karcie gracza" +
                " i na karcie na stole. Wygrywa gracz, który " +
                "odnajdzie najszybciej jak najwięcej symboli." + "\n"
                + "Aby to zrobić klikaj symbole na karcie stosu, które widzisz również na swojej karcie.");
    }

    @FXML
    void setMini1Ist() {
        if(!isSetGroup) setToggleGroup();
        textArea.setText("Każdy z graczy dostaje jedną kartę startową, a pozostałe leżą odkryte w stosie na środku." +
                " Celem jest parowanie kart przeciwnika i powiększanie jego stosów. " +
                "Wygrywa osoba, z najmniejszym stosem na koniec.");
    }

    @FXML
    void setMini2Ist() {
        if(!isSetGroup) setToggleGroup();
        textArea.setText("Każdy z graczy dostaje po jednej karcie, ale dodatkowo kładziemy na środku wspólną kartę. " +
                "Porównujemy ze środkową karty wszystkich graczy, nie tylko swoją. " +
                "Kto znajdzie parę, zabiera kartonik (oprócz środkowego)." +
                " Wygrywa gracz z największą ilością kart.");
    }
}
