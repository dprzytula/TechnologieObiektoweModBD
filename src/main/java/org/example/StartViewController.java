package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.NewTableController.database;

public class StartViewController extends Application {

    @FXML
    private TextField databaseName;

    @FXML
    private Button startCreateDatabase;

    @FXML
    public void getDatabaseName(ActionEvent event) throws IOException {
        database.setDatabaseName(databaseName.getText());
        Stage stage = (Stage)startCreateDatabase.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("NewTableScene.fxml"));
        stage.setTitle("MainApp");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StartView.fxml"));
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
