package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.CreateDatabaseController.database;

public class MenuSceneController {

    @FXML
    void menuAddTable(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent scene = FXMLLoader.load(getClass().getClassLoader().getResource("AddTableScene.fxml"));
        stage.setScene(new Scene(scene, 880,520));
        stage.show();
    }

    @FXML
    void menuRemoveTable(ActionEvent event) throws IOException {

    }

    @FXML
    void menuAddRelation(ActionEvent event) throws IOException {

    }

    @FXML
    void menuDeleteRelation(ActionEvent event) throws IOException {

    }

    @FXML
    void menuGenerateScript(ActionEvent event){
        Generator.generateDatabase(database);
    }

    @FXML
    void menuExit(ActionEvent event) throws IOException {

    }

    @FXML
    void menuViewFullDatabase(ActionEvent event){

        System.out.println(database.databaseName);
        System.out.println(database.tables.get(0).getTableName());
        System.out.println(database.tables.get(1).getTableName());
    }

}
