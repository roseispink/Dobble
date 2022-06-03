module com.example.dobble {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.dobble to javafx.fxml;
    exports com.example.dobble;
    exports com.example.dobble.Controllers;
    opens com.example.dobble.Controllers to javafx.fxml;
    exports com.example.dobble.Network;
    opens com.example.dobble.Network to javafx.fxml;
}