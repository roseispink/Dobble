module com.example.se1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.se1 to javafx.fxml;
    exports com.example.se1;
}