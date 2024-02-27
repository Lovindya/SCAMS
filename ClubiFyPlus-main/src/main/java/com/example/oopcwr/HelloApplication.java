package com.example.oopcwr;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

    primaryStage.setTitle("main");
    Scene scene = new Scene(root);
//    scene.setRoot(root);
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}