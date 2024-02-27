package com.example.oopcwr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Advisormyacc {
    Connection connection;

    @FXML
    private Label adnameval;

    @FXML
    private Button btnadviback;

    @FXML
    private Button btnadviuexit;

    void setName(String name){
        connection = DBConnection.connector();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "";

        adnameval.setText(name);
    }

    @FXML
    void btnadvibackclicked(ActionEvent event) {

    }

    @FXML
    void btnadviexittclicked(ActionEvent event) {

    }

}
