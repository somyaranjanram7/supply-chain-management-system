module com.example.supplychain {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.supplychain to javafx.fxml;
    exports com.example.supplychain;
}