package com.example.oopcwr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JoinClubs implements Initializable {

    private ObservableList<String> clublist = FXCollections.observableArrayList();

    long millis=System.currentTimeMillis();

    // creating a new object of the class Date
    java.sql.Date date = new java.sql.Date(millis);

    Connection connection;

    @FXML
    private ChoiceBox<String> stuclucb;

    @FXML
    private TextField stuidtb;

    @FXML
    private Button btnsback;

    @FXML
    private Button btnssubmit;

    int userId;
    String userName;
    String club;
    int clubId;
    String userReturn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.connector();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `club_id`, `club_name` FROM `club`";
        try {
            preparedStatement = connection.prepareStatement(query);
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
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        stuclucb.setItems(clublist);
    }

    @FXML
    void btnsbackclicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("StudentHome.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Student Home");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }
    }

    @FXML
    void btnsregclicked(ActionEvent event) {
        userName = stuidtb.getText();
        club = stuclucb.getValue();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `stu_name`, `stu_id` FROM `student` WHERE `stu_name` = ?;";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                userReturn = rs.getString(1);
                userId = rs.getInt(2);
            }
        }catch (SQLException e){
            System.out.println(e);
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
            message.showAndWait();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String query2 = "SELECT `club_id` FROM `club` WHERE `club_name` = ?";
        try{
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(club));
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                clubId = rs.getInt(1);
            }
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
            message.showAndWait();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(userReturn != null){
            String query3 = "INSERT INTO `stu_club` (`stu_id`, `club_id`, `date_joined`) VALUES (?,?,?);";
            try{
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, clubId);
                preparedStatement.setString(3, String.valueOf(date));
                preparedStatement.execute();
                Alert message = new Alert(Alert.AlertType.INFORMATION,"Submitted Successfully");
                message.showAndWait();
            }catch (SQLException e){
                System.out.println(e);
                Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
                message.showAndWait();
            }finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else{
            Alert message = new Alert(Alert.AlertType.ERROR,"no such username");
            message.showAndWait();
        }

    }

}
