package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateDatabaseController extends Application {

    static ObservableList<String> options = FXCollections.observableArrayList("");

    static Database database = new Database();

    @FXML
    private TextField databaseName;

    @FXML
    void startCreatingDatabase(ActionEvent event) throws IOException {
        database = Database.builder().databaseName(databaseName.getText()).tables(new ArrayList<>()).build();
        Stage stage = (Stage)(((Node) event.getTarget()).getScene().getWindow());
        Parent scene2 = FXMLLoader.load(getClass().getClassLoader().getResource("MenuScene.fxml"));
        stage.setScene(new Scene(scene2));
        stage.show();
    }

    public static void main(String [] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateDatabase.fxml"));
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
