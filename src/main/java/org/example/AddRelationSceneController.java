package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

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
    private Button addRelationButton;

    @FXML
    private Button closeWindowButton;

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
        Stage stage = (Stage)addRelationButton.getScene().getWindow();
        String firstTableId = "#"+database.tables.stream().filter(table -> table.getTableName()==tableFirst.getSelectionModel().getSelectedItem()).findFirst().get().getId();
        String secondTableId = "#"+database.tables.stream().filter(table -> table.getTableName()==tableSecond.getSelectionModel().getSelectedItem()).findFirst().get().getId();
        relations.add(Relation.addRelation(firstTableId, secondTableId, relationType.getSelectionModel().getSelectedItem().toString()));
        stage.close();
    }

    @FXML
    public void closeAddRelationWindow(ActionEvent action){
        Stage stage = (Stage)closeWindowButton.getScene().getWindow();
        stage.close();
    }


}
