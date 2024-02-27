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

public class HelloController {

    @FXML
    void btnAdvisorClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Advisor.fxml").openStream());
            Scene scene = new Scene(root);
            ownerStage.setTitle("Advisor");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            message.showAndWait();
        }

    }

    @FXML
    void btnRegisterClicked(ActionEvent event) {

    }

    @FXML
    void btnStudentClicked(ActionEvent event) {

    }

}
