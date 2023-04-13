package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.CreateDatabaseController.database;
import static org.example.CreateDatabaseController.options;

public class AddTableSceneController {


    static List<Field> fieldsList = new ArrayList<>();

    @FXML
    private ListView listView;

    @FXML
    private TextField tableName;

    @FXML
    private Button addTableButton;

    @FXML
    private Button addFieldButton;

    @FXML
    void onClickAddTableButton(ActionEvent event){
        database.tables.add(Table.addTable(tableName.getText(), fieldsList));
        Stage stage = (Stage)addTableButton.getScene().getWindow();
        stage.close();
        fieldsList = new ArrayList<>();
    }

    @FXML
    void initialize(){
        listView.setItems(options);
    }

    @FXML
    void onClickAddFieldButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddFieldScene.fxml"));
        stage.setTitle("MainApp");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addFieldButton.getScene().getWindow());
        stage.show();
    }

}
