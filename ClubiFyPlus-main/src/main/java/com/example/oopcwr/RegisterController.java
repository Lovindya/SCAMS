package com.example.oopcwr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    void btnNewAdvisorClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("NewadvisorMembership.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Advisor Membership");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            message.showAndWait();
        }
    }

    @FXML
    void btnNewMembershipClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("NewstudentMembership.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Student Membership");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            message.showAndWait();
        }

    }
    @FXML
    void btnbackClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Main");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }
    }
    @FXML
    void btnregirepoClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("RegReports.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Report");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }

    }


}