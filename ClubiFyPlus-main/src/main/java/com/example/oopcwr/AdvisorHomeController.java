package com.example.oopcwr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdvisorHomeController {
    String name;

    @FXML
    private Button btnback;

    @FXML
    private Button btncreateclubs;

    @FXML
    private Button btnsheduleevents;

    @FXML
    private Button btntrackattendance;

    void setName(String nameG){
        name =  nameG;
    }

    @FXML
    void btnAttTrackingClicked(ActionEvent event) {

    }
    @FXML
    void btnmyaccadvisorClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Advisormyacc.fxml"));
            Parent root = loader.load();
            Advisormyacc controller = loader.getController();
            controller.setName(name);
            Stage ownerStage = new Stage();
            Scene scene = new Scene(root);
            ownerStage.setTitle("My Account");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }
    }


    @FXML
    void btnClubCreationClicked(ActionEvent event) {

    }

    @FXML
    void btnEventSchedulingClicked(ActionEvent event) {

    }

    @FXML
    void btnbackClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("Advisor.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Advisor");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }
    }

}
