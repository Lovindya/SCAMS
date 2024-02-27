package com.example.oopcwr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable{
    String name;

    @FXML
    private Button btnstuback;

    @FXML
    private Button btnstujoin;

    @FXML
    private Button btnstureg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void setName(String nameG){
        name =  nameG;
    }

    @FXML
    void btnStudentregClicked(ActionEvent event) {

    }

    @FXML
    void btnmyacccClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Studentmyacc.fxml"));
            Parent root = loader.load();
            Studentmyacc controller = loader.getController();
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
    void btnStudetbackClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("Student.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Student");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }

    }

    @FXML
    void btnStudetjoinClicked(ActionEvent event) {
        try{
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage ownerStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("joinclubs.fxml"));
            Scene scene = new Scene(root);
            ownerStage.setTitle("Join Clubs");
            ownerStage.setScene(scene);
            ownerStage.show();
        }catch (IOException ex){
            Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
            System.out.println(ex);
            message.showAndWait();
        }

    }
}
