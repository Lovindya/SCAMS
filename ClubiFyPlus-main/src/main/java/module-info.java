module com.example.oopcwr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens com.example.oopcwr to javafx.fxml;
    exports com.example.oopcwr;
}