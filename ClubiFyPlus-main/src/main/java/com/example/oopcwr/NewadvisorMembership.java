package com.example.oopcwr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class NewadvisorMembership {
    Connection connection;

    @FXML
    private ChoiceBox<String> acnamecb;

    @FXML
    private ChoiceBox<Integer> aidcb;

    @FXML
    private TextField anametb;

    @FXML
    private TextField apass;

    @FXML
    private TextField aposition;

    @FXML
    private Button btnback;
    long millis=System.currentTimeMillis();

    // creating a new object of the class Date
    java.sql.Date date = new java.sql.Date(millis);

    private ObservableList<Integer> idlist = FXCollections.observableArrayList();
    private ObservableList<String> clublist = FXCollections.observableArrayList();

    @FXML
    void initialize() throws SQLException {
        connection = DBConnection.connector();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `advisorid`,`advisorname`,`password` FROM `advisor`";
        try {
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int advId = rs.getInt(1);
                idlist.add(advId);
            }
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL Error");
            message.showAndWait();
        }finally {
            preparedStatement.close();
        }
        String query2 = "SELECT `club_id`, `club_name` FROM `club`";
        try {
            preparedStatement = connection.prepareStatement(query2);
            rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int clubId = rs.getInt(1);
                String clubName = rs.getString(2);
                clublist.add(clubName);
            }
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL Error");
            message.showAndWait();
        }finally {
            preparedStatement.close();
        }
        aidcb.setItems(idlist);
        acnamecb.setItems(clublist);
    }

    @FXML
    void btnSubmitClicked(ActionEvent event) throws SQLException {
        String cnamechoice = acnamecb.getValue();
        Integer aidchoice = aidcb.getValue();
        String name= anametb.getText();
        String position = aposition.getText();
        String pass = apass.getText();
        String password = null;
        int clubId = 0;

        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `advisorname`, `password` FROM `advisor` WHERE `advisorid` = ?; ";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(aidchoice));
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                name = rs.getString(1);
                password = rs.getString(2);
            }
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
            message.showAndWait();
        }finally {
            preparedStatement.close();
        }
        String query2 = "SELECT `club_id` FROM `club` WHERE `club_name` = ?";
        try{
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(cnamechoice));
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                clubId = rs.getInt(1);
            }
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
            message.showAndWait();
        }finally {
            preparedStatement.close();
        }

        if (password.equals(pass)){
            String query3 = "INSERT INTO `adv_club` (`adv_id`, `club_id`, `position`, `date_joined`) VALUES (?, ?, ?, ?)  ";
            try{
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setString(1, String.valueOf(aidchoice));
                preparedStatement.setString(2, String.valueOf(clubId));
                preparedStatement.setString(3, position);
                preparedStatement.setString(4, String.valueOf(date));
                preparedStatement.execute();
                Alert message = new Alert(Alert.AlertType.INFORMATION,"Submitted Successfully");
                message.showAndWait();
            }catch (SQLException e){
                System.out.println(e);
                Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
                message.showAndWait();
            }finally {
                preparedStatement.close();
            }
        }
        else{
            Alert message = new Alert(Alert.AlertType.INFORMATION,"Password do not match to account");
            message.showAndWait();
        }


    }

    @FXML
    void btnbackClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Register");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }
    }

}


