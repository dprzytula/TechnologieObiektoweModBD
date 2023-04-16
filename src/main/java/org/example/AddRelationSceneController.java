package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import static org.example.CreateDatabaseController.database;

public class AddRelationSceneController {

    @FXML
    private ComboBox tableFirst;

    @FXML
    private ComboBox tableSecond;

    @FXML
    void initialize(){
        ObservableList<String> tables = FXCollections.observableArrayList();
        database.tables.forEach(table-> tables.add(table.tableName));
        tableFirst.setItems(tables);
        tableSecond.setItems(tables);
    }

    @FXML
    void addRelationToTable(){
        database.tables.forEach(table-> {
            if(table.getTableName()==tableFirst.getSelectionModel().getSelectedItem().toString())
                table.addRelation(Relation.addRelation(tableSecond.getSelectionModel().getSelectedItem().toString()));
        });
    }

}
