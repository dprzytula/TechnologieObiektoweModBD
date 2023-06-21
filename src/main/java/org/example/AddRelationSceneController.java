package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import static org.example.NewTableController.database;
import static org.example.NewTableController.relations;


public class AddRelationSceneController {

    private static ObservableList<String> relationTypes = FXCollections.observableArrayList("1:1", "1:N", "N:1", "N:N");

    @FXML
    private ComboBox tableFirst;

    @FXML
    private ComboBox tableSecond;

    @FXML
    private ComboBox relationType;

    @FXML
    void initialize(){
        ObservableList<String> tables = FXCollections.observableArrayList();
        database.tables.forEach(table-> tables.add(table.tableName));
        tableFirst.setItems(tables);
        tableSecond.setItems(tables);
        relationType.setItems(relationTypes);
    }

    @FXML
    public void addRelationToDb(ActionEvent event){
        relations.add(Relation.addRelation(tableFirst.getId(), tableSecond.getId(), relationType.getSelectionModel().getSelectedItem().toString()));
    }

}
