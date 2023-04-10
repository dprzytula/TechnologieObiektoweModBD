package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    void onClickAddTableButton(ActionEvent event){
        database.tables.add(Table.addTable(tableName.getText(), fieldsList));
        options.add(database.tables.size()-1,database.tables.get(database.tables.size()-1).tableName);
        listView.setItems(options);
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
        stage.show();
    }

}
