package com.example.oopcwr;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connector(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssc_manager","root","");
            return conn;

        }catch (Exception e){
            Alert message = new Alert(Alert.AlertType.ERROR, "Database not found!");
            message.showAndWait();
            return null;
        }
    }
}
