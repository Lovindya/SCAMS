package com.example.oopcwr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Studentmyacc {
    String name;
    Connection connection;

    @FXML
    private Label clubval;

    @FXML
    private Label emailval;

    @FXML
    private Label genderval;

    @FXML
    private Label gradeval;

    @FXML
    private Label nameval;

    @FXML
    private Button btnback;

    @FXML
    private Button btnstuexit;

    @FXML
    private TableView<Map<String, Object>> table;

    @FXML
    private TableColumn<Map<String, Object>, String> colButton;

    @FXML
    private TableColumn<Map<String, Object>, String> colClub;
    @FXML
    private TableColumn<Map<String, Object>, Void> colJoined;

    ObservableList<Map<String, Object>> data = FXCollections.observableArrayList();


    String stuName;
    String gender;
    String email;
    int number;
    int stuId;

    int clubId;

    String classs;

    String grade;
    String date;
    String clubName;

    void setName(String nameG){
        name =  nameG;
        connection = DBConnection.connector();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `stu_name`, `gender`, `email`, `grade`, `class`, `stu_id` FROM `student` WHERE `stu_name` = ?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                stuName = rs.getString(1);
                gender = rs.getString(2);
                email = rs.getString(3);
                classs = rs.getString(4);
                grade = rs.getString(5);
                stuId = rs.getInt(6);
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
        nameval.setText(stuName);
        genderval.setText(gender);
        emailval.setText(email);
        gradeval.setText(classs+grade);
        colClub.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        colJoined.setCellValueFactory(new PropertyValueFactory<>("dateJoin"));
        Map<String, Object> row = new HashMap<>();
        String query2 = "SELECT `club_id`,`date_joined` FROM `stu_club` WHERE `stu_id` = ?;";
        try{
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, stuId);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                clubId = rs.getInt(1);
                date = String.valueOf(rs.getDate(2));
                row.put("dateJoin", date);
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
        String query3 = "SELECT `club_name` FROM `club` WHERE `club_id` = ?;";
        try{
            preparedStatement = connection.prepareStatement(query3);
            preparedStatement.setInt(1, clubId);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                clubName = rs.getString(1);
                row.put("clubID", clubName);
                data.add(row);
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
        table.setItems(data);

    }

    @FXML
    void initialize(){

    }

    @FXML
    void btnbackclicked(ActionEvent event) {

    }

    @FXML
    void btnstuexitclicked(ActionEvent event) {

    }

}

