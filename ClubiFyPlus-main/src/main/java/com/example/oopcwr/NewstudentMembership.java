package com.example.oopcwr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewstudentMembership {
    Connection connection;
    ObservableList gradeList =  FXCollections.observableArrayList("6", "7", "8", "9", "10", "11", "12", "13");
    ObservableList classList =  FXCollections.observableArrayList("A", "B", "C", "D", "E");

    @FXML
    private ChoiceBox<String> sclasscb;

    @FXML
    private ChoiceBox<String> sgradecb;

    @FXML
    private TextField sidtb;

    @FXML
    private TextField smailtb;

    @FXML
    private TextField snametb;

    @FXML
    private TextField snotb;

    @FXML
    private TextField spasstb;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    void initialize(){
        connection = DBConnection.connector();
        sgradecb.setItems(gradeList);
        sclasscb.setItems(classList);
        sgradecb.setValue("6");
        sclasscb.setValue("A");
    }

    @FXML
    void btnSubmitNewMemberClicked(ActionEvent event) throws SQLException {
        String classchoice = sclasscb.getValue();
        String gradechoice = sgradecb.getValue();
        int id = Integer.parseInt(sidtb.getText());
        String name = snametb.getText();
        String email = smailtb.getText();
        int number = Integer.parseInt(snotb.getText());
        String password= spasstb.getText();
        String gender = null;
        if (rb1.isSelected()){
            gender = rb1.getText();
        }
        else if (rb2.isSelected()){
            gender = rb2.getText();
        }

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO `student` (`stu_id`, `stu_name`, `club_name`, `gender`, `email`, `number`, `password`, `grade`, `class`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3,null);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, email);
            preparedStatement.setInt(6, number);
            preparedStatement.setString(7, password);
            preparedStatement.setString(8, gradechoice);
            preparedStatement.setString(9, classchoice);
            preparedStatement.execute();
            Alert message = new Alert(Alert.AlertType.INFORMATION,"Submitted Successfully");
            message.showAndWait();
        }catch (SQLException e){
            Alert message = new Alert(Alert.AlertType.ERROR,"SQL data type mismatch...check your fields");
            message.showAndWait();
        }finally {
            preparedStatement.close();
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

