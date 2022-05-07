module com.example.dobble {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.dobble to javafx.fxml;
    exports com.example.dobble;
}