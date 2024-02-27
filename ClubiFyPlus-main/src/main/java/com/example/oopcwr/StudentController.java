package com.example.oopcwr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class StudentController implements Initializable {
    Connection connection;

    @FXML
    private Button btnback;

    @FXML
    private TextField userId;

    @FXML
    private TextField userPass;

    String name;
    String password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.connector();
    }

    @FXML
    void btnStudentLoginClicked(ActionEvent event) throws SQLException {
        String user = userId.getText();
        String pass = userPass.getText();
        PreparedStatement preparedStatement = null;
        ResultSet rs;
        String query = "SELECT `stu_name`, `password` FROM `student` WHERE `stu_name` = ?;";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
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
        if (pass.equals(password)){
            try{
                ((Node)event.getSource()).getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentHome.fxml"));
                Parent root = loader.load();
                StudentHomeController controller = loader.getController();
                controller.setName(name);
                Stage ownerStage = new Stage();
                Scene scene = new Scene(root);
                ownerStage.setTitle("Student Home");
                ownerStage.setScene(scene);
                ownerStage.show();
            }catch (IOException ex){
                Alert message = new Alert(Alert.AlertType.ERROR, "Loading Failure");
                message.showAndWait();
            }
        }
        else {
            Alert message = new Alert(Alert.AlertType.ERROR, "Recheck credentials");
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
            message.showAndWait();
        }
    }

}
