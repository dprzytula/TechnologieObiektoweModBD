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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.CreateDatabaseController.database;

public class Main {

    @FXML
    private TextField name0;

    @FXML
    private TextField name1;

    @FXML
    private TextField name2;

    @FXML
    private TextField name3;

    @FXML
    private TextField name4;

    @FXML
    private TextField name5;

    @FXML
    private TextField name6;

    @FXML
    private ComboBox type0;

    @FXML
    private ComboBox type1;

    @FXML
    private ComboBox type2;

    @FXML
    private ComboBox type3;

    @FXML
    private ComboBox type4;

    @FXML
    private ComboBox type5;

    @FXML
    private ComboBox type6;

    @FXML
    private Button addTableButton;

    @FXML
    private Button generateScriptButton;


    @FXML
    private Button deleteTableButton;

    @FXML
    private Button addRelationButton;


    Table table = new Table();





    @FXML
    void onClickGenerateScriptButton(ActionEvent event){
        Generator.generateDatabase(database);
    }

//    @FXML
//    void onClickAddTableButton(ActionEvent event){
//        List<Field> fieldsList = new ArrayList<>();
//        for(int i=0;i<3;i++){
//            fieldsList.add(Field.createField(name0.getCharacters().toString(), name1.getCharacters().toString()));
//        }
//        database.tables.add(Table.addTable(fieldsList));
//
//    }







    @FXML
    void onClickViewTablesButton(ActionEvent event){

        System.out.println(database.databaseName);
        System.out.println(database.tables.get(0).getTableName());
//        System.out.println(database.tables.get(0).tableName);
//        System.out.println(database.tables.get(0).fields.get(0).getFieldName());
    }

    @FXML
    void onClickViewAddTableForm(ActionEvent event){
        name2.setVisible(true);
        name3.setVisible(true);
        name4.setVisible(true);
        name5.setVisible(true);
        name6.setVisible(true);
        type0.setVisible(true);
        type1.setVisible(true);
        type2.setVisible(true);
        type3.setVisible(true);
        type4.setVisible(true);
        type5.setVisible(true);
        type6.setVisible(true);
        addTableButton.setVisible(true);
        generateScriptButton.setVisible(true);
        deleteTableButton.setVisible(true);
        addRelationButton.setVisible(false);
    }

    @FXML
    void onClickViewAddRelationForm(ActionEvent event) throws NoSuchFieldException, IllegalAccessException, IOException {
        Stage stage = new Stage();
        stage = (Stage)(((Node) event.getTarget()).getScene().getWindow());

//        name2.setVisible(false);
//        name3.setVisible(false);
//        name4.setVisible(false);
//        name5.setVisible(false);
//        name6.setVisible(false);
//        type0.setVisible(false);
//        type1.setVisible(false);
//        type2.setVisible(false);
//        type3.setVisible(false);
//        type4.setVisible(false);
//        type5.setVisible(false);
//        type6.setVisible(false);
//        addTableButton.setVisible(false);
//        generateScriptButton.setVisible(false);
//        deleteTableButton.setVisible(false);
//        addRelationButton.setVisible(true);
        Parent scene2 = FXMLLoader.load(getClass().getClassLoader().getResource("Scene2.fxml"));
        stage.setScene(new Scene(scene2, 1280,720));
        stage.show();
    }

    public void onStart(){
        ObservableList<String> dataTypes = FXCollections.observableArrayList("INTEGER","VARCHAR(255)","DECIMAL","DATE");
        type0.setItems(dataTypes);
        type1.setItems(dataTypes);
        type2.setItems(dataTypes);
        type3.setItems(dataTypes);
        type4.setItems(dataTypes);
        type5.setItems(dataTypes);
        type6.setItems(dataTypes);
    }
}